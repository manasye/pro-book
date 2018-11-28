cd book-ws

# Setup mysql
if [ $DB_PASSWORD ]
then
  mysql -u "$DB_USERNAME" -p"$DB_PASSWORD" -e "# ISI SINI JANG"
else
  mysql -u "$DB_USERNAME" -e "# ISI SINI JANG"
fi