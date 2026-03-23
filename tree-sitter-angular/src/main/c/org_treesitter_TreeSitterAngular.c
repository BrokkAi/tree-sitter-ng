
#include <jni.h>
void *tree_sitter_angular();
/*
 * Class:     org_treesitter_TreeSitterAngular
 * Method:    tree_sitter_angular
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_treesitter_TreeSitterAngular_tree_1sitter_1angular
  (JNIEnv *env, jclass clz){
   return (jlong) tree_sitter_angular();
}
