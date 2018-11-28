cd book-ws

# Setup mysql CEK JANG BENER APA KAGA
if [ $DB_PASSWORD ]
then
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "drop database bookws;"
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "create database bookws;"
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" bookws < db.sql
else
  mysql -u "$DB_USERNAME" -e "drop database bookws;"
  mysql -u "$DB_USERNAME" -e "create database bookws;"
  mysql -u "$DB_USERNAME" bookws < db.sql
fi