ls -1 *.json | sed 's/.json$//' | while read col; do 
    mongoimport -d migi-database -c $col < $col.json; 
done

ls -1 *.csv | sed 's/.csv$//' | while read col; do
    mongoimport -d migi-database -c $col --type csv --file "$col.csv" --headerline;
done
