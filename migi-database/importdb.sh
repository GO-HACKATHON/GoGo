ls -1 *.json | sed 's/.json$//' | while read col; do 
    mongoimport -d migi-database -c $col < $col.json; 
done
