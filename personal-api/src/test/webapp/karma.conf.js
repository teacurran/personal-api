// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html
// Generated on 2015-06-01 using
// generator-karma 1.0.0

module.exports = function(config) {
  'use strict';

  config.set({
    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // base path, that will be used to resolve files and exclude
    basePath: '../',

    // testing framework to use (jasmine/mocha/qunit/...)
    // as well as any additional frameworks (requirejs/chai/sinon/...)
    frameworks: [
      "jasmine"
    ],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      '../../target/grunt/build/scripts/components/jquery/dist/jquery.js',
      '../../target/grunt/build/scripts/components/angular/angular.js',
      '../../target/grunt/build/scripts/components/bootstrap-sass-official/assets/javascripts/bootstrap.js',
      '../../target/grunt/build/scripts/components/angular-animate/angular-animate.js',
      '../../target/grunt/build/scripts/components/angular-cookies/angular-cookies.js',
      '../../target/grunt/build/scripts/components/angular-resource/angular-resource.js',
      '../../target/grunt/build/scripts/components/angular-route/angular-route.js',
      '../../target/grunt/build/scripts/components/angular-sanitize/angular-sanitize.js',
      '../../target/grunt/build/scripts/components/angular-touch/angular-touch.js',
      '../../target/grunt/build/scripts/components/angular-mocks/angular-mocks.js',
      // endbower
      '../main/webapp/scripts/app.js',
      '../main/webapp/scripts/controllers/*.js',
      "webapp/scripts/**/*.js",
      "webapp/mock/**/*.js",
      "webapp/spec/**/*.js"
    ],

    // list of files / patterns to exclude
    exclude: [
    ],

    // web server port
    port: 8083,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: [
      "PhantomJS"
    ],

    // Which plugins to enable
    plugins: [
      "karma-phantomjs-launcher",
      "karma-jasmine"
    ],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false,

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,

    // Uncomment the following lines if you are using grunt's server to run the tests
    // proxies: {
    //   '/': 'http://localhost:9000/'
    // },
    // URL root prevent conflicts with the site root
    // urlRoot: '_karma_'
  });
};
