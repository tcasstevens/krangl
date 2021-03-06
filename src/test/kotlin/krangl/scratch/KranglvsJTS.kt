package krangl.scratch

import krangl.*
import java.io.File
import java.time.LocalDate


/**
 * @author Holger Brandl
 */

fun main(args: Array<String>) {
    // https://jtablesaw.wordpress.com/an-introduction/


    val inputFile = File("/Users/brandl/projects/kotlin/misc/tablesaw/data/1950-2014_torn.csv")

    //    val tornadoes = Dataframe(Table.read().csv(tornandoCsv))
    val tornadoes = DataFrame.readCSV(inputFile)

    //    tornadoes.columnNames();
    tornadoes.names

    //    tornadoes.shape();
    // DOES NOT EXIST

    //    tornadoes.structure();
    tornadoes.schema()
    columnTypes(tornadoes).print()
    //    tornadoes.structure().selectWhere(column("Column Type").isEqualTo("INTEGER"));
    //    tornadoes.first(3);
    //
    //    val month = tornadoes.target.dateColumn("Date").month()
    //
    //    // todo does this work by ref?
    //    tornadoes.target.addColumn(month);

    tornadoes.addColumn("month") { it["Date"].asType<LocalDate>().map { it?.month } };
    //
    //    tornadoes.target.removeColumn("State No");
    tornadoes.remove { listOf("State No") }
    tornadoes.select { startsWith("St") }
    //
    //
    //    //parser
    //    tornadoes.target.sortOn("-Fatalities");
    tornadoes.sortedByDescending("Fatalities")

    //    tornadoes.target.column("Fatalities").summary().print();
    tornadoes["Fatalities"].median()

    //
    //    //filter
    //    tornadoes.target.selectWhere(column("Fatalities").isGreaterThan(0));
    tornadoes.filter { it["Fatalities"].greaterThan(0) AND it["Fatalities"].greaterThan(0) }

    //    tornadoes.target.selectWhere(column("Width").isGreaterThan(300))
    //
    //    tornadoes.target.selectWhere(column("Width").isGreaterThan(300))
    //
    //    tornadoes.target.selectWhere(either(
    //            column("Width").isGreaterThan(300),   // 300 yards
    //            column("Length").isGreaterThan(10)
    //    ))  // 10 miles
    //
    //    tornadoes.target.select("State", "Date").where(column("Date").isInQ2());
    //
    //
    //    CrossTab.xCount(tornadoes.target, tornadoes.target.categoryColumn("State"), tornadoes.target.shortColumn("Scale"));
    tornadoes.count("State", "Scale")

    //
    //    tornadoes.exportToCsv("data/rev_tornadoes_1950-2014.csv");


}