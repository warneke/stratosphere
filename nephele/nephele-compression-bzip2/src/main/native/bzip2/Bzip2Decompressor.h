/***********************************************************************************************************************
 *
 * Copyright (C) 2010-2012 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

/* DO NOT EDIT THIS FILE - it is machine generated */

#ifndef __de_tu_berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor__
#define __de_tu_berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor__

#include <jni.h>

#ifdef __cplusplus
extern "C"
{
#endif

JNIEXPORT void JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_initIDs (JNIEnv *env, jclass);
JNIEXPORT void JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_init (JNIEnv *env, jclass, jint);
JNIEXPORT void JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_finish (JNIEnv *env, jobject, jint);
JNIEXPORT void JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_finishAll (JNIEnv *env, jclass);
JNIEXPORT jint JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_decompressBytesDirect (JNIEnv *env, jobject, jint);
JNIEXPORT jint JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_getAmountOfConsumedInput (JNIEnv *env, jobject, jint);
JNIEXPORT jint JNICALL Java_de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_getAmountOfConsumedOutput (JNIEnv *env, jobject, jint);
#undef de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_SIZE_LENGTH
#define de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_SIZE_LENGTH 8L
#undef de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_UNCOMPRESSED_BLOCKSIZE_LENGTH
#define de_tu_1berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor_UNCOMPRESSED_BLOCKSIZE_LENGTH 4L

#ifdef __cplusplus
}
#endif

#endif /* __de_tu_berlin_cit_nephele_io_compression_library_bzip2_Bzip2Decompressor__ */
