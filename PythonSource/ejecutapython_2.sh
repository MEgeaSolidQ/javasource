rm -r salida
yarn jar /usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming-*.jar -input /usr/files/apat63_99.txt -output salida -mapper MEGCountryClaimsMapper.py -reducer MEGCountryClaimsReducer.py 

