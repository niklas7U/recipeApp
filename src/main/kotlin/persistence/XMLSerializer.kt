package persistence

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import models.Ingredient
import models.Recipe
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import kotlin.jvm.Throws

class XMLSerializer(private val file: File) : Serializer {
    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(DomDriver())
        xStream.allowTypes(arrayOf(Recipe::class.java, Ingredient::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }
    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val outputStream = XStream(DomDriver()).createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}
