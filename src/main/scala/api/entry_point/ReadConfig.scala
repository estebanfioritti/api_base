package scala.api.entry_point

import api.module.shared.infraestructure.config.DbConfig
import com.typesafe.config.{Config, ConfigFactory}

import java.io
import java.nio.file.{Files, Path, Paths}
import scala.api.entry_point.ReadConfig.{AppConfig, ParameterConfig}
import scala.jdk.CollectionConverters.IteratorHasAsScala

class ReadConfig {

  def execute(args: Array[String]): ParameterConfig = getConfig(args) match {
    case Some(appConfig) =>
      val actorSystemName: String = appConfig.appConfig.getString("main-actor-system.name")
      val host: String            = appConfig.serverConfig.getString("http-server.host")
      val port: Int               = appConfig.serverConfig.getInt("http-server.port")
      ParameterConfig(actorSystemName, host, port, appConfig.dbConfig)
    case None            =>
      println("No se encontraron los archivos de configuración necesarios")
      throw new Exception("No se encontraron los archivos de configuración necesarios")
  }

  private def getConfig(args: Array[String]): Option[AppConfig] = if (args.isEmpty) {
    println("Leyendo argumentos por defecto")
    val appConfig: Config    = ConfigFactory.load("application")
    val serverConfig: Config = ConfigFactory.load("http-server")
    val dbConfig: DbConfig   = DbConfig(appConfig.getConfig("database"))
    Some(AppConfig(appConfig, serverConfig, dbConfig))
  } else {
    println(s"Leyendo argumentos ${args(0)}")
    val folderName: String      = args(0)
    val configFolder: Path      = Paths.get(folderName)
    val files: List[Path]       = Files.list(configFolder).iterator().asScala.toList
    val fileList: List[io.File] = files.map(_.toFile)
    println(s"Se encontraron los archivos de configuración ${fileList.map(_.getName)}")
    readConfig(fileList)
  }

  private def readConfig(files: List[io.File]): Option[AppConfig] = for {
    appfileConf    <- files.find(_.getName == "application.conf")
    serverFileConf <- files.find(_.getName == "http-server.conf")
    dbFileConf     <- files.find(_.getName == "database.conf")
  } yield {
    println(
      s"Se encontraron los archivos de configuración ${appfileConf.getName}, ${serverFileConf.getName}, ${dbFileConf.getName}"
    )
    val appConfig            = ConfigFactory.parseFile(appfileConf)
    val serverConfig: Config = ConfigFactory.parseFile(serverFileConf)
    val dbConfig: DbConfig   = DbConfig(ConfigFactory.parseFile(dbFileConf).getConfig("database"))
    AppConfig(appConfig, serverConfig, dbConfig)
  }

}

object ReadConfig {

  case class ParameterConfig(actorSystemName: String, host: String, port: Int, dbConfig: DbConfig)

  private case class AppConfig(appConfig: Config, serverConfig: Config, dbConfig: DbConfig)

}
