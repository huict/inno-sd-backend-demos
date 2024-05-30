[ -d "/raw/tennis_atp" ] || git clone https://github.com/JeffSackmann/tennis_atp.git /raw/tennis_atp
[ -d "/raw/tennis_wta" ] || git clone https://github.com/JeffSackmann/tennis_wta.git /raw/tennis_wta

cd /raw/tennis_wta
git checkout b9b31dc539ae7c21168debdde02bf94159e8585c
cd /raw/tennis_atp 
git checkout 3a02d8f0ca706a979dd2ef74c52e618878a08ad4


# https://github.com/JeffSackmann/tennis_slam_pointbypoint ook gaaf, maar lastig te matchen op de andere repos
# De Ids van matches en dergelijke komen niet overeen:(