package kotlin.io

import java.io.File

/**
 * Iterates over elements of file, e.g. for /foo/bar/gav
 * we will have /, /foo, /foo/bar, /foo/bar/gav
 */
class FileIterator(private val f: File): Iterator<File> {

    private val path = f.separatorsToSystem()

    private var separatorIndex = -1

    private var nextIndex = -1

    override fun next(): File {
        val prevIndex = separatorIndex
        separatorIndex = nextIndex
        return File(path.substring(prevIndex+1, nextIndex))
    }

    override fun hasNext(): Boolean {
        if (nextIndex == separatorIndex) {
            // /foo/bar/gav/
            if (nextIndex >= path.length()-1)
                return false
            nextIndex = path.indexOf(File.separatorChar, separatorIndex+1)
            // To remove ""
            // To remove things like C:, http: before first slash
            if (nextIndex==separatorIndex+1 || (nextIndex > 0 && path[nextIndex-1]==':')) {
                separatorIndex = nextIndex
                return hasNext()
            }
        }
        if (nextIndex == -1)
            nextIndex = path.length()
        return true
    }
}