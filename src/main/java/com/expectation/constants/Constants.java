package com.expectation.constants;

public class Constants {

    public static final String DEFAULT_DIR = System.getProperty("user.dir")+"/src/main/resources/%s/%s/%s.scala";


    public static String IMPORTS = "import com.zeotap.expectations.column.dsl.ColumnExpectation\n" +
            "import com.zeotap.expectations.column.helper.ColumnExpectationUtils.ColumnExpectationOps\n" +
            "import com.zeotap.utility.spark.generator.RandomDataGenerator\n" +
            "import com.zeotap.utility.spark.ops.DataColumnOps._\n" +
            "import com.zeotap.utility.spark.types.{DColumn, SparkDataframe}\n" +
            "import com.zeotap.zeoflow.zeocore.mapper.data.expectation.framework.common.DataPartnerExpectation\n" +
            "import com.zeotap.zeoflow.zeocore.mapper.data.expectation.framework.utils.CommonUtils._\n" +
            "\n" +
            "class %s extends DataPartnerExpectation {\n\n";

    public static String COLUMN = "column(\"%s\", List(\"";
    public static String TABLE = "val %s: Array[DColumn] = Array(\n";

    public static String POST_EXPECTATION = "override def getRawDataDefinition(region: String, product: String): Map[String, SparkDataframe] = (region, product) match {\n" +
            "    case (\"%s\", \"%s\") => getSparkDataFrameMap[DColumn, SparkDataframe](List(%s))\n" +
            "    case _ => Map(\"emptyDataset\" -> SparkDataframe())\n" +
            "  }\n"+
            "}";
}
