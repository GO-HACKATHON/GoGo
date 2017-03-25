ls -1 *.json | sed 's/.json$//' | while read col; do 
    mongoimport -d test -c bus < $col.json; 
done
