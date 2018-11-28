cd pro-book
source .ethes
export $(cut -d= -f1 .ethes)

# Setup mysql
if [ $DB_PASSWORD ]
then
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "drop database probook;"
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "create database probook;"
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" probook < src/model/probook.sql
else
  mysql -u "$DB_USERNAME" -e "drop database probook;"
  mysql -u "$DB_USERNAME" -e "create database probook;"
  mysql -u "$DB_USERNAME" probook < src/model/probook.sql
fi

# unset DB_USERNAME
# unset DB_PASSWORD
# unset DB_HOST
# unset DB_USERNAME
# unset PORT