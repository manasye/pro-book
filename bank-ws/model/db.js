const Sequelize = require('sequelize')
const dotenv = require('dotenv').config()

if (dotenv.error) {
    throw dotenv.error
}

const db = new Sequelize({
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    host: process.env.DB_HOST,
    dialect: 'mysql',
    'pool': {}
})

module.exports = db