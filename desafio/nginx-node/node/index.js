const express = require('express');
const app = express();
const port = 8080;
const mysql = require('mysql');
const config = {
    host: 'db',
    user: 'root',
    password: 'root',
    database: 'nodedb'
};

const queryDB = (conn, cfg, query) => { 
    const connection = conn.createConnection(cfg);
    connection.query(query);
    connection.end();
};

const consultDB = (conn, cfg, res) => { 
    const connection = conn.createConnection(cfg);
    connection.query("SELECT * FROM people", function (error, result) {
      if (error) {
        res.json(error);
      } else {
        let text = '<h1>Full Cycle Rocks!</h1>\n';
        text += JSON.stringify(result, null, '\t');
        return res.send(text);
      }
    });
    connection.end();
};
const createTable = 'CREATE TABLE IF NOT EXISTS `people`' +
        '(id integer not null auto_increment primary key, name varchar(255));';
queryDB(mysql, config, createTable);

app.get('/', (req, res) => { 
    const sql = "INSERT INTO people (name) VALUES ('vitor')";
    queryDB(mysql, config, sql);
    consultDB(mysql, config, res);
});

app.listen(port, () => { 
    console.log('listen at ' + port);
});