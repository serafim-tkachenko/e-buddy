#!/bin/bash

init_status=$(mongo --host mongo --quiet --eval "rs.status().ok")

until [ "${init_status}" = "1" ]
do
    echo "Current INIT status is ${init_status}, waiting for code 1"
    sleep 10
    init_status=$(mongo --host mongo --quiet --eval "rs.status().ok")
done

echo "Start MongoDB initialization scripts..."

echo "Allowing Mongo DB to obtain any queries from secondary replicas..."
mongo --host mongo --eval "rs.secondaryOk();"
echo "Secondary replicas were activated for being queried"

echo "Creating DB schema if needed and switching to it..."
mongo --host mongo --eval "db.getSiblingDB(\"service_db\");"
echo "Current DB was successfully created/switched to."

echo "Adding service user to the database..."
mongo service_db --host mongo --eval "db.createUser({ user: \"service_user\", pwd: \"12345\", roles: [ { role: \"readWrite\", db: \"service_db\" } ] });" 
echo "Service user was added."

echo "Adding required collections if needed..."
mongo service_db --host mongo --eval "db.createCollection(\"employee\");" 
echo "All required collections were added."

echo "All Mongo DB initialization scripts passed"
