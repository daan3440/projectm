# projectm
Pvt73Back - ProjectM


## Commands
All follow the pattern of
ReturnValue Path Arguments
Argument with ? may or may not be included
String /hejSQL
List<Challenges> /challenges
ResponseEntity<Challenge> /challenge/{id} int ID 
String /addChallenge int TrailID, int ChallengeID, String Name
String /updateChallenge int id, ? int TrailID, ? int caid, ? String name
Map<String, Boolean> /deleteChallenge/{id} int id
List<ChallengeAttributes> /challengeAttributes
ResponseEntity<ChallengeAttributes> /challengeAttributes/{id} int id
String /addChallengeAttributes ? int count, ? BigInteger time, String startDate, String endDate
String /updateChallengeAttributes int id, ? int count, ? BigInteger time, ? String startDate, ? String endDate, ? boolean complete
Map<String, Boolean> /deleteChallengeAttributes/{id}  int id
List<ChallengeConnector> /challengeConnector
ResponseEntity<ChallengeConnector> /challengeConnector/{cid} int cid
String /addChallengeConnector int caid, int cid, int tid
String /updateChallengeConnector int caid, int cid, int tid
Map<String, Boolean> /deleteChallengeConnector/{id} int cid
List<GroupChallengeConnect> /groupChallengeConnect 
ResponseEntity<GroupChallengeConnect> /groupChallengeConnect/{cid} int cid
String /addGroupChallengeConnect int gid, int cid
String /updateGroupChallengeConnect int gid, int cid
Map<String, Boolean> /deleteGroupChallengeConnect/{cid} int cid
List<TrailReview> /trailReview
ResponseEntity<TrailReview> /trailReview/{tid} int tid
String /addTrailReview int tid, int uid, ? String review, ? int rating, ? String date, ? String title
String /updateTrailReview int tid, int uid, ? String review, ? int rating, ? String date, ? String title
Map<String, Boolean> /deleteTrailReview/{id} int tid
List<UserRuns> /userRuns 
ResponseEntity<UserRuns> /userRuns/{id} int id
Iterable<UserRuns> /leaderboard/{tid} int tid
String /addUserRun int uid, int tid, String date, BigInteger time, int length, String comment
String /updateUserRun int id, int uid, int tid, ? String date, ? BigInteger time, ? int length, ? String Comment
Map<String, Boolean> /deleteUserRuns/{id} int id
List<UserGroup> /allUserGroups
ResponseEntity<UserGroup> /userGroup/{id} int id
String /addUserGroup int uid, String groupname
String /updateUserGroup int id, ? String groupname
Map<String, Boolean> /deleteUserGroup/{id} int id
List<UserGroupConnect> /userGroupConnect 
ResponseEntity<UserGroupConnect> /userGroupConnect/{uid} int uid
String /addUserGroupConnect int uid, int gid
Map<String, Boolean> /deleteUserGroupConnect/{uid} int uid
List<UserAdminGroup> /userAdminGroup 
ResponseEntity<UserAdminGroup> /userAdminGroup/{uid} int uid
String /addUserAdminGroup int uid, int gid
String /updateUserAdminGroup int uid, int gid
Map<String, Boolean> /deleteUserAdminGroup/{uid} int uid
List<UserTrails> /userTrails
ResponseEntity<List<UserTrails>> /userTrails/{uid} int uid
ResponseEntity<List<UserTrails>> /usersFavTrails/{uid} int uid
String /addUserTrails int tid, int uid, boolean favourite
String /updateUserTrails int tid, int uid, ? boolean favourite
Map<String, Boolean> /deleteUserTrails/{id} int id
List<User> /allUser
ResponseEntity<User> /user/{id} int id
String /addUser String fName, String lName, String email, ? String tagline, ? String photo
String /updateUser int id, ? String fName, ? String lName, String email, ? String tagline, ? String photo
Map<String, Boolean> /deleteUser/{id} int id
ResponseEntity<Trail> /trail/{id} int id
ResponseEntity<List<UserTrails>> /addFavourite int uid, int tid
Iterable<User> /allUsers
Iterable<UserGroup> /allGroups
List<Trail> /allTrails
Iterable<Trail> /allTrailsSortedByLocation double lat double lon
String /addTrails
String /updateTrail
Optional<Trail> /getTrailById/{id} int id
String /dropTrail/{id} int id
String /dropUser/{id} int id
double /getDistance double x1, double y1, double x2, double y2
