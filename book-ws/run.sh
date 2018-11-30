cd book-ws
source src/main/envs/.env
export $(cut -d= -f1 src/main/envs/.env)

# Run app
# TODO: Belom tau gimana run javanya
rm -rf "$CATALINA_HOME/webapps/ROOT"
rm -rf "$CATALINA_HOME/work"
cp target/book-ws-0.0.war "$CATALINA_HOME/webapps/ROOT.war"
catalina run