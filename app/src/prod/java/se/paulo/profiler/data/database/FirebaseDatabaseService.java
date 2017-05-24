package se.paulo.profiler.data.database;

/** * Created by Paulo Vila Nova on 2017-05-23.
 */

public class FirebaseDatabaseService implements DatabaseSource {

    private static final String USER_PROFILES = "user_profiles";

    private FirebaseDatabaseService() {

    }

    public static DatabaseSource getInstance() {
        return new FirebaseDatabaseService();
    }

    @Override
    public Completable createProfile(final Profile profile) {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter e) throws Exception {
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        final DatabaseReference idRef = rootRef.child(USER_PROFILES).child(profile.getUid());
                        idRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (!snapshot.exists()) {
                                    idRef.setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                e.onComplete();
                                            } else {
                                                e.onError(task.getException());
                                            }
                                        }
                                    });
                                } else {
                                    e.onComplete();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("FIREBASE", databaseError.toString());
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Maybe<Profile> getProfile(final String uid) {
        return Maybe.create(
                new MaybeOnSubscribe<Profile>() {
                    @Override
                    public void subscribe(final MaybeEmitter e) throws Exception {
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference idRef = rootRef.child(USER_PROFILES).child(uid);
                        idRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            //does this check node for activeUser exists?
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    //setUpProfilePageComponent(
                                    Profile profile = snapshot.getValue(Profile.class);
                                    e.onSuccess(profile);
                                } else {
                                    e.onComplete();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("FIREBASE", databaseError.toString());
                            }
                        });
                    }
                }
        );
    }

    @Override
    public Completable deleteProfile(final String uid) {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter e) throws Exception {
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        rootRef.child(USER_PROFILES)
                                .child(uid)
                                .setValue(null)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            e.onComplete();
                                        } else {
                                            e.onError(task.getException());
                                        }
                                    }
                                });
                    }

                });
    }

    @Override
    public Completable updateProfile(final Profile profile) {
        return Completable.create(
                new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(final CompletableEmitter e) throws Exception {
                        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        rootRef.child(USER_PROFILES)
                                .child(profile.getUid())
                                .setValue(profile)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            e.onComplete();
                                        } else {
                                            e.onError(task.getException());
                                        }
                                    }
                                });
                    }

                });
    }

    @Override
    public void setReturnFail(boolean bool) {
        //only for testing purposes
    }

    @Override
    public void setReturnEmpty(boolean bool) {
        //only for testing purposes

    }

}