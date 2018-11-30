chmod +x ./bank-ws/run.sh
chmod +x ./book-ws/run.sh
chmod +x ./pro-book/run.sh

tmux split-window -h ./bank-ws/run.sh
tmux split-window ./pro-book/run.sh
./book-ws/run.sh