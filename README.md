# Microsoft Excel Sheet To Objects Example

This is a very simple example to show how to convert Microsoft Excel (xlsx) sheet to list of objects using Apache POI

To run a test sample:<br />
Copy the file testSheet.xlsx to a readable directory and set it's path in file Entry.java, then run it.

The idea is simply to define annotation `@ExcelCellInfo` on the fields you want to map the sheet columns to.<br />
This annotation has required attibute `index` that defines which column index in the sheet to map to this field.
There is another attribute 'cellParser' to define a custom mapping between the cell's content and the field, this `cellParser` attribute has
default value `DefaultCellParser` which will auto-bind common types (string, integer, long, double, date, and boolean).<br />
There are also some custom parsers as samples (such as `CellPercentageParser`)<br />

**Usage example:**<br />

```
ExcelSheetDescriptor<RowClassSample> sheetDescriptor = new ExcelSheetDescriptor<>(RowClassSample.class).setHasHeader();
List<RowClassSample> rows = ExcelUtils.readFirstSheet("pathToFile.xlsx", sheetDescriptor);
```

<br />

And the class to bind to:<br />
```
public class RowClassSample {

	@ExcelCellInfo(index = 0)
	private long serial;

	@ExcelCellInfo(index = 1)
	private String name;

	@ExcelCellInfo(index = 2, cellParser = CellNumericAsStringParser.class)
	private String registrationNumber;

	@ExcelCellInfo(index = 3, cellParser = CellPercentageParser.class)
	private Double percentage;

	@ExcelCellInfo(index = 6)
	private String reason;

	@ExcelCellInfo(index = 4)
	private String notes;

	@ExcelCellInfo(index = 5, cellParser = CellBooleanYesNoArParser.class)
	private boolean approval;
	
	// getters & setters
}
```
