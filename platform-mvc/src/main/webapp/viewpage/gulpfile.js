var gulp = require('gulp');
var inject = require('gulp-inject');
var order = require("gulp-order");
var rename = require("gulp-rename");
var angularFilesort = require('gulp-angular-filesort');
var browserSync = require("browser-sync");
var reload = browserSync.reload;
var useref = require('gulp-useref');
var jsdoc = require('gulp-jsdoc3');
var ngAnnotate = require('gulp-ng-annotate');
var copy = require('gulp-copy');
var fileCopy = require('gulp-file-copy');
var imagemin = require('gulp-imagemin');
var filter = require('gulp-filter');
var csso = require('gulp-csso');
var uglify = require('gulp-uglify');
var gulpIf = require('gulp-if');
var print = require('gulp-print');
var rev = require('gulp-rev');
var revReplace = require('gulp-rev-replace');
var pngquant = require('imagemin-pngquant');
var htmlmin = require('gulp-minify-html');
var templateCache = require('gulp-angular-templatecache');

var projectUrl = "app";

var devCssFilter = filter([projectUrl + "/components/**/*.css", projectUrl + "/pages/**/*.css", projectUrl + "/styles/**/*.css"]);
var devJsFilter = filter([projectUrl + "/components/**/*.js", projectUrl + "/pages/**/*.js"]);
var releaseCssFilter = filter(projectUrl + "/dst/**/*.css", {restore: true});
var releaseJsFilter = filter(projectUrl + "/dst/**/*.js", {restore: true});
var releaseJsCssFilter = filter([projectUrl + "/dst/**/*.css", projectUrl + "/dst/**/*.js"], {restore: true});

gulp.task('index', function () {
    var target = gulp.src(projectUrl +'/index.html');
    // It's not necessary to read the files (will speed up things), we're only after their paths: 
    var sources = gulp.src([projectUrl +'/scripts/*.js'], {read: false});

    return target.pipe(inject(sources))
        .pipe(gulp.dest('./src'));
    // return traget.pipe()
});

gulp.task('order', function(){
    var target = gulp.src(projectUrl +'/index.html');
    // It's not necessary to read the files (will speed up things), we're only after their paths: 
    var sources = gulp.src([projectUrl +'/scripts/*.js'], {read: false});
    gulp
        .src("./open-platform/scripts/*.js")
        // .pipe(gulp.src("**/*.js")) // gulp.src passes through input
        .pipe(order([
            projectUrl +"/scripts/app.js",
        ]))
    target.pipe(gulp.src("./open-platform/scripts/*.js"))
        .pipe(order([
            projectUrl +"/scripts/app.js",
        ]))
        .pipe(inject())
        .pipe(gulp.dest('./src'))
        // .pipe(gulp.dest("dist"));
    // return target.pipe(inject(order))
    //     .pipe(gulp.dest('./src'));
})
gulp.task('cssOrder', function(){
    gulp.src(projectUrl +'/dev.html')
        .pipe(inject(
            gulp.src([projectUrl +'/**/*.css']).
            pipe(order([
                projectUrl +'/styles/reset.css',
                projectUrl +'/styles/bootstrap.min.css',
                projectUrl +'/styles/bootstrap-extend.min.css',
                projectUrl +'/styles/site-reset.css',
            ])), {relative: true}
        ))
        // .pipe(inject(
        //     gulp.src([projectUrl +'/**/*.css']), {relative: true}
        // ))
        .pipe(rename('index.html'))
        .pipe(gulp.dest(projectUrl +''));
})
gulp.task('indexChange', function(){
    gulp.src(projectUrl +'/dev.html')
        .pipe(inject(
            gulp.src([projectUrl +'/**/*.js', '!' + projectUrl + '/dst/**/*.js',  '!' + projectUrl + '/ngAnnotate/**/*.js']).pipe(angularFilesort()), {relative: true}
        ))
        .pipe(inject(
            gulp.src([projectUrl +'/styles/**/*.css',projectUrl +'/components/**/*.css', projectUrl +'/pages/**/*.css']), {relative: true}
        ))
        // .pipe(inject(
        //     gulp.src([projectUrl +'/pages/**/*.css']), {relative: true}
        // ))
            
        // .pipe(inject(
        //     gulp.src([projectUrl +'/pages/**/*.css']), {relative: true}
        // ))
        .pipe(rename('index.html'))
        .pipe(gulp.dest(projectUrl +''))
        .pipe(reload({stream:true}));
})


// 监听scss和html文件, 当文件发生变化后做些什么！
gulp.task('serve', ['indexChange'] ,function () {

    // 从这个项目的根目录启动服务器
    browserSync({
        server: {
            baseDir: "./"
        }
    });
    // gulp.watch(projectUrl +"/scripts/*.js").on("add", function () {
    //     console.log(11111111111111111111111111111111111111111111111);
    //     console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
    // });
    gulp.watch(projectUrl +"/dev.html", ['indexChange']);
    gulp.watch([projectUrl +"/components/**/*.css", projectUrl +"/pages/**/*.css", projectUrl +"/styles/**/*.css"],
        ['indexChange']);
    // gulp.watch([projectUrl +"/pages/**/*.css"], ['indexChange']);
    // gulp.watch([projectUrl +"/components/**/*.css"], ['indexChange']);
    // gulp.watch([projectUrl +"/styles/**/*.css"], ['indexChange']);
    // gulp.watch(projectUrl +"/styles/**/*.css", function(){
    //     browserSync.reload();
    // });
    gulp.watch([projectUrl +"/**/*.html", "!" + projectUrl + "/dev.html"], function(){
        browserSync.reload();
    });
        // .on("change", browserSync.reload);
    gulp.watch([projectUrl +'/components/**/*js'], ['indexChange']);
    gulp.watch([projectUrl +'/pages/**/*js'], ['indexChange']);
    //     function (event) {
    //     browserSync.reload();
    //     console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
    // });
});
gulp.task('useref', function () {
    return gulp.src(projectUrl +'/index.html')
        // .pipe(useref({searchPath:["app/css/1.css"]}))
        // .pipe(useref({searchPath:["app/css/*.css"]}))
        .pipe(rename('release.html'))
        .pipe(useref())
        // .pipe(rename('release.html'))
        .pipe(gulp.dest(projectUrl +''));
});
gulp.task('ngAnnotate', function(){
    return gulp.src([projectUrl +'/**/*.js', '!' + projectUrl + '/ngAnnotate/**/*.js', '!' + projectUrl + '/dst/**/*.js']).
    pipe(ngAnnotate()).
    pipe(gulp.dest(projectUrl + '/ngAnnotate'))
})
gulp.task('release', ['template','imagemin','ngAnnotate'], function(){
// gulp.task('release', ['imagemin'], function(){
// gulp.task('release',  function(){
    gulp.src(projectUrl +'/dev.html')
        .pipe(inject(
            gulp.src([projectUrl +'/ngAnnotate/**/*.js']).
            // gulp.src([projectUrl +'/**/*.js', '!' + projectUrl + '/dst/**/*.js']).
            // pipe(ngAnnotate()).
            pipe(angularFilesort()), {relative: true}
        ))
        .pipe(inject(
            gulp.src([projectUrl +'/components/**/*.css', projectUrl +'/pages/**/*.css', projectUrl +'/styles/**/*.css']), {relative: true}
        ))
        .pipe(rename('release.html'))
        .pipe(useref())
        .pipe(gulpIf('dst/**/*.css', csso()))
        .pipe(gulpIf('dst/**/*.js', uglify()))
        .pipe(releaseJsCssFilter)
        .pipe(rev())
        .pipe(releaseJsCssFilter.restore)
        .pipe(revReplace())
        .pipe(gulp.dest(projectUrl +''))
})
gulp.task('doc', function (cb) {
    gulp.src(['README.md', projectUrl +'/components/button/button.js'], {read: false})
    // gulp.src(['README.md', projectUrl +'/scripts/app.js'], {read: false})
        .pipe(jsdoc(cb));
    // var config = require('./jsdoc.json');
    // gulp.src(['README.md', './src/**/*.js'], {read: false})
    //     .pipe(jsdoc(config, cb));
});
gulp.task('copyImg', function () {
    var start = projectUrl + '/**/*.{jpg,png,gif,jpeg}';
    gulp.src(start)
        .pipe(fileCopy(projectUrl + '/images',{
            start:start
        }));
});
gulp.task('imagemin', function() {
    var start = projectUrl + '/**/*.{jpg,png,gif,jpeg}';
    var end = projectUrl +  '/dst/images';
    gulp.src(start)
        .pipe(rename({dirname: ''}))
        .pipe(imagemin({
            progressive: true,
            // svgoPlugins: [{removeViewBox: false}],//不要移除svg的viewbox属性
            use: [pngquant()] //使用pngquant深度压缩png图片的imagemin插件
        }))
        .pipe(gulp.dest(end));
})
gulp.task('template', function () {
    return gulp.src(['./app/components/**/*.html', './app/pages/**/*.html'])
        .pipe(htmlmin({collapseWhitespace: true}))
        .pipe(
            templateCache({
                transformUrl: function(url) {
                    if(url.indexOf('businessComponents') >= 0 || url.indexOf('uiComponents') >= 0 || url.indexOf('_core') >= 0){
                        url = 'components/' + url;
                    }else{
                        url = 'pages/' + url;
                    }
                    return url
                },
                module: 'templates'
            }))
        .pipe(gulp.dest('./app/ngAnnotate/components/_core'));
});
gulp.task('default',['serve']);