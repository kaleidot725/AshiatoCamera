package kaleidot725.highestpeaks

import kaleidot725.michetimer.model.repository.PictureRepositoryImpl
import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import java.io.File
import java.nio.file.Paths

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PictureRepositoryImplUnitTest {

    private val currentDirectory = Paths.get("").toAbsolutePath().toString()
    private val directory = "tests"
    private val directoryPath = "${currentDirectory}/${directory}"
    private val names = listOf("A", "B", "C", "D", "E")
    private lateinit var repositoryImpl : PictureRepositoryImpl

    fun createFilePath(name : String) : String { return "${directoryPath}/${name}" }

    @Before
    fun initialize() {
        repositoryImpl = PictureRepositoryImpl(directoryPath)
        File(directoryPath).mkdir()
        for (name in names) {
            File(createFilePath(name)).createNewFile()
        }
    }

    @After
    fun finalize() {
        File(directoryPath).delete()
    }

    @Test
    fun all() {
        val pictures = repositoryImpl.all()
        for (name in names ) {
            val picture = pictures.find { it.path == createFilePath(name) }
            Assert.assertEquals(true, picture != null)
            Assert.assertEquals(createFilePath(name), picture?.path)
            Assert.assertEquals(name, picture?.name)
        }
    }

    @Test
    fun count() {
        val pictures = repositoryImpl.all()
        Assert.assertEquals(names.count(), pictures.count())
    }
}
