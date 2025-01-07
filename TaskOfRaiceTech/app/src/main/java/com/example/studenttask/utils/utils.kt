package com.example.studenttask.utils

import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.util.UUID
import javax.security.auth.callback.Callback

//fun uploadimg(uri:URI,folderName:String,callback:(String?)->Unit){
//    var imageURL:String?=null
//    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
//        .putFile(uri).addOnSuccessListener { it.storage.downloadUrl.addOnSuccessListener { imageURL = it.toString() } }
//
//}

//fun uploadimg(uri: URI, folderName: String, callback: (String?) -> Unit) {
//    var imageURL: String? = null
//
//    FirebaseStorage.getInstance().getReference(folderName)
//        .child(UUID.randomUUID().toString())
//        .putFile(uri)
//        .addOnSuccessListener { taskSnapshot ->
//            // Image uploaded successfully
//            taskSnapshot.storage.downloadUrl.addOnSuccessListener {
//                // Get the download URL
//                imageURL = it.toString()
//
//                // Invoke the callback with the imageURL
//                callback(imageURL)
//            }
//        }
//        .addOnFailureListener { exception ->
//            // Handle any errors that occurred during the upload
//            exception.printStackTrace()
//
//            // Invoke the callback with null to indicate failure
//            callback(null)
//        }
//}


import android.net.Uri

//fun uploadimg(uri: Uri, folderName: String, callback: (String?) -> Unit) {
//    var imageURL: String? = null
//
//    FirebaseStorage.getInstance().getReference(folderName)
//        .child(UUID.randomUUID().toString())
//        .putFile(uri)
//        .addOnSuccessListener { taskSnapshot ->
//            // Image uploaded successfully
//            taskSnapshot.storage.downloadUrl.addOnSuccessListener {
//                // Get the download URL
//                imageURL = it.toString()
//
//                // Invoke the callback with the imageURL
//                callback(imageURL)
//            }
//        }
//        .addOnFailureListener { exception ->
//            // Handle any errors that occurred during the upload
//            exception.printStackTrace()
//
//            // Invoke the callback with null to indicate failure
//            callback(null)
//        }
//}
