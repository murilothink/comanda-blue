
// WEBPACK is used to compile JavaScript modules
// REF: https://webpack.js.org/guides/getting-started/#using-a-configuration

var path = require('path');

module.exports = {

    // Where to look for react application
    entry: ['./src/main/js/src/index.js'],

    // This option controls if and how source maps are generated
    // Creates sourcemaps so that, when you are debugging JS code in the browser,
    // you can link back to original source code
    devtool: 'sourcemaps',

    // Cache the generated webpack modules and chunks to improve build speed
    cache: true,

    // Providing the mode configuration option tells webpack to use its built-in optimizations accordingly.
    // 'development': Sets process.env.NODE_ENV on DefinePlugin to value development. Enables NamedChunksPlugin and NamedModulesPlugin
    mode: 'production',

    // The top-level output key contains set of options instructing webpack on how and where it
    // should output your bundles, assets and anything else you bundle or load with webpack.
    // In fact it compiles ALL of the JavaScript bits into ./src/main/resources/static/built/bundle.js,
    // which is a JavaScript equivalent to a Spring Boot uber JAR.
    // All your custom code AND the modules pulled in by the require() calls are stuffed into this file.
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: [/(node_modules)/, /\.(png|jpe?g|gif)$/i],
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env"
                                , "@babel/preset-react"
                                ],
                        plugins: [ "@babel/plugin-proposal-class-properties"
                                  ,["@babel/plugin-proposal-decorators", { "legacy": true }]
                                  , "@babel/plugin-transform-runtime"
                                 ]
                    },
                }]
            },
            {
                    test: /\.(png|jpe?g|gif)$/i,
                    use: [
                      {
                        loader: 'file-loader',
                        options: {
                           name: '[name].[ext]',
                           publicPath: 'img',
                           emitFile: false
                        }
                      },
                    ],
            },
            {
                    test: /\.css$/i,
                    use: ['style-loader', 'css-loader'],
            },
        ]

    }
};