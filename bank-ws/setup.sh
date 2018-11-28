cd bank-ws
source .env
export $(cut -d= -f1 .env)

# Setup mysql
if [ $DB_PASSWORD ]
then
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "drop database bankws;"
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "create database bankws;"
else
  mysql -u "$DB_USERNAME" -e "drop database bankws;"
  mysql -u "$DB_USERNAME" -e "create database bankws;"
fi

# Run sequelize setup
node models/setup.js

# unset DB_USERNAME
# unset DB_PASSWORD
# unset DB_HOST
# unset DB_USERNAME
# unset PORT