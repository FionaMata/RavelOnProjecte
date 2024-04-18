package com.mataecheverry.project_ravelry.dades.autenticacio

import com.google.firebase.firestore.FirebaseFirestore
import com.mataecheverry.project_ravelry.dades.app_models.AppPattern
import com.mataecheverry.project_ravelry.dades.app_models.AppPatternAuthor
import com.mataecheverry.project_ravelry.dades.app_models.AppProject
import com.mataecheverry.project_ravelry.dades.app_models.AppUser
import com.mataecheverry.project_ravelry.dades.app_models.AppYarn
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirestoreManager() {

    private val USERS = "Users"
    private val PATTERN_AUTHORS = "Pattern Authors"
    //private val SHOPS = "Shops"
    //private val CATEGORIES = "Categories"
    private val PROJECTS = "Projects"
    private val PATTERNS = "Patterns"
    private val YARNS = "Yarns"
    private val firestore = FirebaseFirestore.getInstance()


    //Tots els .ADD
    suspend fun addUser(user: AppUser){
        val ref = firestore.collection(USERS)
            .document(user.id.toString())
//        val newUser = user.copy(id=ref.id)
//        ref.set(newUser).await()
    }

    suspend fun addPatternAuthor(patternAuthor: AppPatternAuthor){
        val ref= firestore.collection(PATTERN_AUTHORS)
            .document(patternAuthor.id.toString())
//        val newPatternAuthor = patternAuthor.copy(id=ref.id)
//        ref.set(newPatternAuthor).await()
    }

    suspend fun addProjects(project: AppProject){
        val ref= firestore.collection(PROJECTS)
            .document(project.id.toString())
//        val newProject = project.copy(id=ref.id)
//        ref.set(newProject).await()
    }


    suspend fun addPatterns(pattern: AppPattern){
        val ref= firestore.collection(PATTERNS)
            .document(pattern.id.toString())
//        val newPattern= pattern.copy(id=ref.id)
//        ref.set(newPattern).await()
    }

    suspend fun addYarn(yarn: AppYarn){
        val ref= firestore.collection(PATTERNS)
            .document(yarn.id.toString())
//        val newYarn = yarn.copy(id=ref.id)
//        ref.set(newYarn).await()
    }

    //Obtenció de tots els FLOWS
    fun getUserFlow():
            Flow<List<AppUser>> = callbackFlow {
        val userRef = firestore.collection(USERS)
            .orderBy("id")

        val subscription = userRef
            .addSnapshotListener { snapshot, exception ->
                snapshot?.let {
                    val users = mutableListOf<AppUser>()
                    for (document in snapshot) {
                        val user = document.toObject(AppUser::class.java)
                        user.let { users.add(it) }
                    }
                    trySend(users)
                }
            }
        awaitClose(){
            subscription.remove()
        }
    }

    fun getPatternAuthorFlow():
            Flow<List<AppPatternAuthor>> = callbackFlow {
        val patternAuthorRef = firestore.collection(PATTERN_AUTHORS)
            .orderBy("id")

        val subscription = patternAuthorRef
            .addSnapshotListener { snapshot, exception ->
                snapshot?.let {
                    val patternAuthors = mutableListOf<AppPatternAuthor>()
                    for (document in snapshot) {
                        val patternAuthor = document.toObject(AppPatternAuthor::class.java)
                        patternAuthor.let { patternAuthors.add(it) }
                    }
                    trySend(patternAuthors)
                }
            }
        awaitClose(){
            subscription.remove()
        }
    }

    fun getProjectFlow():
            Flow<List<AppProject>> = callbackFlow {
        val projectRef = firestore.collection(PROJECTS)
            .orderBy("id")

        val subscription = projectRef
            .addSnapshotListener { snapshot, exception ->
                snapshot?.let {
                    val projects = mutableListOf<AppProject>()
                    for (document in snapshot) {
                        val project = document.toObject(AppProject::class.java)
                        project.let { projects.add(it) }
                    }
                    trySend(projects)
                }
            }
        awaitClose(){
            subscription.remove()
        }
    }

    fun getPattern():
            Flow<List<AppPattern>> = callbackFlow {
        val patternRef = firestore.collection(PATTERNS)
            .orderBy("id")

        val subscription = patternRef
            .addSnapshotListener { snapshot, exception ->
                snapshot?.let {
                    val patterns = mutableListOf<AppPattern>()
                    for (document in snapshot) {
                        val pattern = document.toObject(AppPattern::class.java)
                        pattern.let { patterns.add(it) }
                    }
                    trySend(patterns)
                }
            }
        awaitClose(){
            subscription.remove()
        }
    }

    fun getYarnFlow():
            Flow<List<AppYarn>> = callbackFlow {
        val yarnRef = firestore.collection(YARNS)
            .orderBy("id")

        val subscription = yarnRef
            .addSnapshotListener { snapshot, exception ->
                snapshot?.let {
                    val yarns = mutableListOf<AppYarn>()
                    for (document in snapshot) {
                        val yarn = document.toObject(AppYarn::class.java)
                        yarn.let { yarns.add(it) }
                    }
                    trySend(yarns)
                }
            }
        awaitClose(){
            subscription.remove()
        }
    }

    //FALTA FER CONNEXIONS ENTRE DIFERENTS COL·LECCIONS

}