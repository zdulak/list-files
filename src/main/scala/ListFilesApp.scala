import java.io.File
import scala.sys.exit

object ListFilesApp extends App {
  if (args.length != 1) {
    println("You must enter one parameter which is name of a directory")
    exit(-1)
  }

  val directory = new File(args.head)
  if (!(directory.exists && directory.isDirectory)) {
    println("The directory name is invalid")
    exit(-1)
  }

  getListFiles(directory, recursive = true).foreach(println)

  def getListFiles(directory: File, recursive: Boolean): List[String] = {
    val content = directory.listFiles
    val files =  content.filter(_.isFile).map(_.getPath).toList
    if (recursive) {
      val subdirectories = content.filter(_.isDirectory)
      files ++ subdirectories.flatMap(getListFiles(_, recursive = true))
    }
    else {
      files
    }
  }
}
