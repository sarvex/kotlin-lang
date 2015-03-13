package kotlin.io

import java.io.IOException

private fun constructMessage(file: String, other: String?, reason: String?): String {
    val sb = StringBuilder(file)
    if (other != null) {
        sb.append(" -> $other")
    }
    if (reason != null) {
        sb.append(": $reason")
    }
    return sb.toString()
}

/**
 * A base exception class for file system exceptions
 */
open public class FileSystemException(public val file: String,
                                      private val other: String? = null,
                                      public val reason: String? = null
                                     ) : IOException(constructMessage(file, other, reason)) {
    public val otherFile: String? = other
}

/**
 * An exception class which is used when some file to create or copy to already exists
 */
public class FileAlreadyExistsException(file: String,
                                        other: String? = null,
                                        reason: String? = null) : FileSystemException(file, other, reason)

/**
 * An exception class which is used when user tries to replace non-empty directory by file
 *
 * Note: looks like very non-standard thing
 */
public class DirectoryNotEmptyException(file: String,
                                        other: String? = null,
                                        reason: String? = null) : FileSystemException(file, other, reason)

/**
 * An exception class which is used when we have not enough access for some operation
 */
public class AccessDeniedException(file: String,
                                   other: String? = null,
                                   reason: String? = null) : FileSystemException(file, other, reason)

/**
 * An exception class which is used when file to copy does not exist
 */
public class NoSuchFileException(file: String,
                                 other: String? = null,
                                 reason: String? = null) : FileSystemException(file, other, reason)

/**
 * An exception class which is used when user tries to copy some directory as a file
 *
 * Note: why not to copy the whole directory in this case
 */
public class FileIsDirectoryException(file: String,
                                      other: String? = null,
                                      reason: String? = null) : FileSystemException(file, other, reason)
