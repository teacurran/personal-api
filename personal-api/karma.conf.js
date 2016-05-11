module.exports = function(config) {
    config.set({

        // base path, that will be used to resolve files and exclude
        basePath: '.',

        // frameworks to use
        frameworks: ['jasmine', 'jsmockito-jshamcrest'],

        // list of files / patterns to load in the browser
        files: [
        ],

        // list of files to exclude
        exclude: [
        ],

        // test results reporter to use
        reporters: ['progress', 'coverage'],

        preprocessors: {
            // source files, that you wanna generate coverage for
            // do not include tests or libraries
            // (these files will be instrumented by Istanbul)
            //'src/main/webapp/js/**/*.js': ['coverage']
        },

        // web server port
        port: 9876,

        // enable / disable colors in the output (reporters and logs)
        colors: true,

        // level of logging
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // Start these browsers
        browsers: ['PhantomJS'],

        // If browser does not capture in given timeout [ms], kill it
        captureTimeout: 60000,

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: true,

        // optionally, configure the reporter
        coverageReporter: {
            type: 'html',
            dir: 'target/coverage/',
            check: {
                global: {
                    statements: 20,
                    lines: 20,
                    functions: 20,
                    branches: 0
                }
            }
        }
    });
};
