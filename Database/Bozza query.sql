select r1.raceId as id1, r2.raceId as id2, count(*) as cnt
from results as r1, results as r2, races as rr1, races as rr2
where r1.raceId > r2.raceId
and r1.statusId=1
and r2.statusId=1
and rr1.raceId=r1.raceId
and rr2.raceId=r2.raceId
and r1.driverId=r2.driverId
and rr1.year=2000
and rr1.year=rr2.year
group by id1, id2