import com.zeotap.expectations.column.dsl.ColumnExpectation
import com.zeotap.expectations.column.helper.ColumnExpectationUtils.ColumnExpectationOps
import com.zeotap.utility.spark.generator.RandomDataGenerator
import com.zeotap.utility.spark.ops.DataColumnOps._
import com.zeotap.utility.spark.types.{DColumn, SparkDataframe}
import com.zeotap.zeoflow.zeocore.mapper.data.expectation.framework.common.DataPartnerExpectation
import com.zeotap.zeoflow.zeocore.mapper.data.expectation.framework.utils.CommonUtils._

class Bidberry extends DataPartnerExpectation {

val bidberry_rawInput_eu_profile: Array[DColumn] = Array(
    column("values", List("1","2","3","4","5","6","7")).withNull.withJunk,
    column("country", List("gbr","esp","deu","fra","ita")))

val bidberry_maidInput_eu_profile: Array[DColumn] = Array(
    column("_c0", List("iPhone","Unknown","X88 Pro","SmartTV","To Be Filled By O.E.M.","HP Notebook","iPad")).withNull,
    column("_c1", List("DEU","GBR","ITA","FRA","ESP")).withJunk)

override def getRawDataDefinition(region: String, product: String): Map[String, SparkDataframe] = (region, product) match {
    case ("eu", "profile") => getSparkDataFrameMap[DColumn, SparkDataframe](List(("inputDf",bidberry_rawInput_eu_profile),("maid",bidberry_maidInput_eu_profile)))
    case _ => Map("emptyDataset" -> SparkDataframe())
  }
}
