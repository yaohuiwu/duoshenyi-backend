#!/bin/sh

#初始化数据库环境
dbName="backend"
dbHost="localhost"
dbUser="root"
dbPasswd="pekall1234"

mysql -h $dbHost -u $dbUser -p$dbPasswd <<EOF
	create database if not exists ${dbName};
EOF
