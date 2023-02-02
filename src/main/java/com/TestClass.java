package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.excel.ExcelSheetDescriptor;
import com.utils.excel.ExcelUtils;

public class TestClass {

	public static void main(String[] arfs) throws Exception {

		ExcelSheetDescriptor<SCData> sheetDescriptor = new ExcelSheetDescriptor<>(SCData.class).setHasHeader();
		List<SCData> rows = ExcelUtils.readFirstSheet("C:\\Users\\91970\\Desktop\\cbmsData.xlsx", sheetDescriptor);
//		for (SCData row : rows) {
//			System.out.println(row);
//		}

		HashMap<String, List<SCData>> map = new LinkedHashMap<>();

		for (SCData row : rows) {
			if (!map.containsKey(row.getAkAppNum())) {
				List<SCData> scDataList = new ArrayList<>();
				scDataList.add(row);
				map.put(row.getAkAppNum(), scDataList);
			} else {
				List<SCData> scData = map.get(row.getAkAppNum());
				scData.add(row);
				map.put(row.getAkAppNum(), scData);
			}
		}
		System.out.println(map.size());
		List<CBMSData> listData = jsonData();
		Map<String, CBMSData> cbmsMap = new LinkedHashMap<>();
		for (CBMSData cbmsData : listData) {
			cbmsMap.put(cbmsData.getCode(), cbmsData);
		}

		Map<String, List<SCData>> singleGarauteeMap = new HashMap<>();
		Map<String, List<SCData>> twoGarauteeMap = new HashMap<>();
		Map<String, List<SCData>> threeGarauteeMap = new HashMap<>();
		Map<String, List<SCData>> fourGarauteeMap = new HashMap<>();
		Map<String, List<SCData>> fiveGarauteeMap = new HashMap<>();
		Map<String, List<SCData>> zeroGarauteeMap = new HashMap<>();

		for (Entry<String, List<SCData>> mapData : map.entrySet()) {
			if (mapData.getValue().size() == 1) {
				zeroGarauteeMap.put(mapData.getKey(), mapData.getValue());
//				System.out.println(mapData.getValue());
//				System.out.println(mapData.getValue().size());
			}else if (mapData.getValue().size() == 2) {
				singleGarauteeMap.put(mapData.getKey(), mapData.getValue());
			} else if (mapData.getValue().size() == 3) {
				twoGarauteeMap.put(mapData.getKey(), mapData.getValue());
			} else if (mapData.getValue().size() == 4) {
				threeGarauteeMap.put(mapData.getKey(), mapData.getValue());
			} else if (mapData.getValue().size() == 5) {
				fourGarauteeMap.put(mapData.getKey(), mapData.getValue());
			} else if (mapData.getValue().size() == 6) {
				fiveGarauteeMap.put(mapData.getKey(), mapData.getValue());
			}else if(mapData.getValue().size() >6) {
				System.out.println(mapData.getValue());
			}
		}
		
//		System.out.println(singleGarauteeMap.size());
//		System.out.println(twoGarauteeMap.size());
//		System.out.println(threeGarauteeMap.size());
//		System.out.println(fourGarauteeMap.size());
//		System.out.println(fiveGarauteeMap.size());
//		System.out.println(singleGarauteeMap.size());
//		singleGarantee(singleGarauteeMap, cbmsMap);
//		twoGarantee(twoGarauteeMap, cbmsMap);
//		threeGarantee(threeGarauteeMap, cbmsMap);
//		fourGarantee(fourGarauteeMap, cbmsMap);
//		zeroGarantee(zeroGarauteeMap, cbmsMap);
//		fiveGarantee(fiveGarauteeMap, cbmsMap);

	}
	
	public static void fiveGarantee(Map<String, List<SCData>> threeGarauteeMap, Map<String, CBMSData> cbmsMap)
			throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("fiveGaurantee");
		int rowCount = 0;
		for (Entry<String, List<SCData>> mapData : threeGarauteeMap.entrySet()) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (int i = 0; i <= 13; i++) {
				SCData mainData = mapData.getValue().get(0);
				SCData gauranteeData = mapData.getValue().get(1);
				SCData gauranteeData2 = mapData.getValue().get(2);
				SCData gauranteeData3 = mapData.getValue().get(3);
				SCData gauranteeData4 = mapData.getValue().get(4);
				SCData gauranteeData5 = mapData.getValue().get(5);
				Cell cell = row.createCell(columnCount);
				if (i == 0) {
					cell.setCellValue(mainData.getAkAppNum());
				}else if (i == 1) {
					cell.setCellValue(mainData.getBcbIdNo1());
				} else if (i == 2) {
					cell.setCellValue(mainData.getBcbIdNo2());
				} else if (i == 3) {
					cell.setCellValue(gauranteeData.getBcbIdNo1());
				} else if (i == 4) {
					cell.setCellValue(gauranteeData2.getBcbIdNo1());
				}else if (i == 5) {
					cell.setCellValue(gauranteeData3.getBcbIdNo1());
				}else if (i == 6) {
					cell.setCellValue(gauranteeData4.getBcbIdNo1());
				}else if (i == 7) {
					cell.setCellValue(gauranteeData5.getBcbIdNo1());
				}else if (i == 8) {
					cell.setCellValue(mainData.getCothIsic());
				} else if (i == 9) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getLable());
				} else if (i == 10) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getCbmsCode());
				} else if (i == 11) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactor());
				} else if (i == 12) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactorInPercentage());
				} else if (i == 13) {
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					cell.setCellValue(mainData.getVintageDat());
					cell.setCellStyle(cellStyle);
					cell.setCellValue(mainData.getVintageDat());
				}
				columnCount++;
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("D:/test/fiveGaurantee.xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.close();
		}
	}

	
	public static void fourGarantee(Map<String, List<SCData>> threeGarauteeMap, Map<String, CBMSData> cbmsMap)
			throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("fourGaurantee");
		int rowCount = 0;
		for (Entry<String, List<SCData>> mapData : threeGarauteeMap.entrySet()) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (int i = 0; i <= 12; i++) {
				SCData mainData = mapData.getValue().get(0);
				SCData gauranteeData = mapData.getValue().get(1);
				SCData gauranteeData2 = mapData.getValue().get(2);
				SCData gauranteeData3 = mapData.getValue().get(3);
				SCData gauranteeData4 = mapData.getValue().get(4);
				Cell cell = row.createCell(columnCount);
				if (i == 0) {
					cell.setCellValue(mainData.getAkAppNum());
				}else if (i == 1) {
					cell.setCellValue(mainData.getBcbIdNo1());
				} else if (i == 2) {
					cell.setCellValue(mainData.getBcbIdNo2());
				} else if (i == 3) {
					cell.setCellValue(gauranteeData.getBcbIdNo1());
				} else if (i == 4) {
					cell.setCellValue(gauranteeData2.getBcbIdNo1());
				}else if (i == 5) {
					cell.setCellValue(gauranteeData3.getBcbIdNo1());
				}else if (i == 6) {
					cell.setCellValue(gauranteeData4.getBcbIdNo1());
				}else if (i == 7) {
					cell.setCellValue(mainData.getCothIsic());
				} else if (i == 8) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getLable());
				} else if (i == 9) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getCbmsCode());
				} else if (i == 10) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactor());
				} else if (i == 11) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactorInPercentage());
				} else if (i == 12) {
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					cell.setCellValue(mainData.getVintageDat());
					cell.setCellStyle(cellStyle);
					cell.setCellValue(mainData.getVintageDat());
				}
				columnCount++;
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("D:/test/fourGaurantee.xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.close();
		}
	}

	public static void zeroGarantee(Map<String, List<SCData>> zeroGarauteeMap, Map<String, CBMSData> cbmsMap)
			throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("zerogaruntee");
		int rowCount = 0;
		for (Entry<String, List<SCData>> mapData : zeroGarauteeMap.entrySet()) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (int i = 0; i <= 8; i++) {
				SCData mainData = mapData.getValue().get(0);
				Cell cell = row.createCell(++columnCount);
				if (i == 0) {
					cell.setCellValue(mainData.getAkAppNum());
				} else if (i == 1) {
					cell.setCellValue(mainData.getBcbIdNo1());
				} else if (i == 2) {
					cell.setCellValue(mainData.getBcbIdNo2());
				} else if (i == 3) {
					cell.setCellValue(mainData.getCothIsic());
				} else if (i == 4) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getLable());
				} else if (i == 5) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getCbmsCode());
				} else if (i == 6) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactor());
				} else if (i == 7) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactorInPercentage());
				} else if (i == 8) {
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					cell.setCellValue(mainData.getVintageDat());
					cell.setCellStyle(cellStyle);
					cell.setCellValue(mainData.getVintageDat());
				}

			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("D:/test/zerogaruntee.xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.close();
		}
	}
	
	public static void twoGarantee(Map<String, List<SCData>> twoGarauteeMap, Map<String, CBMSData> cbmsMap)
			throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("twoGarantee");
		int rowCount = 0;
		for (Entry<String, List<SCData>> mapData : twoGarauteeMap.entrySet()) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (int i = 0; i <= 10; i++) {
				SCData mainData = mapData.getValue().get(0);
				SCData gauranteeData = mapData.getValue().get(1);
				SCData gauranteeData2 = mapData.getValue().get(2);
				Cell cell = row.createCell(columnCount);
				if (i == 0) {
					cell.setCellValue(mainData.getAkAppNum());
				}else if (i == 1) {
					cell.setCellValue(mainData.getBcbIdNo1());
				} else if (i == 2) {
					cell.setCellValue(mainData.getBcbIdNo2());
				} else if (i == 3) {
					cell.setCellValue(gauranteeData.getBcbIdNo1());
				} else if (i == 4) {
					cell.setCellValue(gauranteeData2.getBcbIdNo1());
				} else if (i == 5) {
					cell.setCellValue(mainData.getCothIsic());
				} else if (i == 6) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getLable());
				} else if (i == 7) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getCbmsCode());
				} else if (i == 8) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactor());
				} else if (i == 9) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactorInPercentage());
				} else if (i == 10) {
//					System.out.println(mainData.getVintageDat());
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					cell.setCellValue(mainData.getVintageDat());
					cell.setCellStyle(cellStyle);
					cell.setCellValue(mainData.getVintageDat());
				}
				columnCount++;
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("D:/test/twoGarantee.xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.close();
		}
	}
	
	public static void threeGarantee(Map<String, List<SCData>> threeGarauteeMap, Map<String, CBMSData> cbmsMap)
			throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("threeGarantee");
		int rowCount = 0;
		for (Entry<String, List<SCData>> mapData : threeGarauteeMap.entrySet()) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (int i = 0; i <= 11; i++) {
				SCData mainData = mapData.getValue().get(0);
				SCData gauranteeData = mapData.getValue().get(1);
				SCData gauranteeData2 = mapData.getValue().get(2);
				SCData gauranteeData3 = mapData.getValue().get(3);
				Cell cell = row.createCell(columnCount);
				if (i == 0) {
					cell.setCellValue(mainData.getAkAppNum());
				}else if (i == 1) {
					cell.setCellValue(mainData.getBcbIdNo1());
				} else if (i == 2) {
					cell.setCellValue(mainData.getBcbIdNo2());
				} else if (i == 3) {
					cell.setCellValue(gauranteeData.getBcbIdNo1());
				} else if (i == 4) {
					cell.setCellValue(gauranteeData2.getBcbIdNo1());
				}else if (i == 5) {
					cell.setCellValue(gauranteeData3.getBcbIdNo1());
				}else if (i == 6) {
					cell.setCellValue(mainData.getCothIsic());
				} else if (i == 7) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getLable());
				} else if (i == 8) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getCbmsCode());
				} else if (i == 9) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactor());
				} else if (i == 10) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactorInPercentage());
				} else if (i == 11) {
//					System.out.println(mainData.getVintageDat());
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					cell.setCellValue(mainData.getVintageDat());
					cell.setCellStyle(cellStyle);
					cell.setCellValue(mainData.getVintageDat());
				}
				columnCount++;
			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("D:/test/threeGarantee.xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.close();
		}
	}

	public static void singleGarantee(Map<String, List<SCData>> singleGarauteeMap, Map<String, CBMSData> cbmsMap)
			throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("singleGaurantee");
		int rowCount = 0;
		for (Entry<String, List<SCData>> mapData : singleGarauteeMap.entrySet()) {
			Row row = sheet.createRow(++rowCount);
			int columnCount = 0;
			for (int i = 0; i <= 9; i++) {
				SCData mainData = mapData.getValue().get(0);
				SCData gauranteeData = mapData.getValue().get(1);
				Cell cell = row.createCell(++columnCount);
				if (i == 0) {
					if(mainData.getBcbIdType().equals("04") || mainData.getBcbIdType().equals("4") || mainData.getBcbIdType().equals("11") || mainData.getBcbIdType().equals("02") || mainData.getBcbIdType().equals("2")) {
						System.out.println(mainData);
					}
					if(gauranteeData.getBcbIdType().equals("04") || gauranteeData.getBcbIdType().equals("4")|| gauranteeData.getBcbIdType().equals("11") || gauranteeData.getBcbIdType().equals("02") || gauranteeData.getBcbIdType().equals("2")) {
						System.out.println(gauranteeData);
					}
					cell.setCellValue(mainData.getAkAppNum());
				} else if (i == 1) {
					cell.setCellValue(mainData.getBcbIdNo1());
				} else if (i == 2) {
					cell.setCellValue(mainData.getBcbIdNo2());
				} else if (i == 3) {
					cell.setCellValue(gauranteeData.getBcbIdNo1());
				} else if (i == 4) {
					cell.setCellValue(mainData.getCothIsic());
				} else if (i == 5) {
//					System.out.println(mainData.getCothIsic());
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getLable());
				} else if (i == 6) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getCbmsCode());
				} else if (i == 7) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactor());
				} else if (i == 8) {
					cell.setCellValue(cbmsMap.get(mainData.getCothIsic()).getIncomeFactorInPercentage());
				} else if (i == 9) {
//					System.out.println(mainData.getVintageDat());
					CellStyle cellStyle = workbook.createCellStyle();
					CreationHelper createHelper = workbook.getCreationHelper();
					cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
					cell.setCellValue(mainData.getVintageDat());
					cell.setCellStyle(cellStyle);
					cell.setCellValue(mainData.getVintageDat());
				}

			}
		}
		try (FileOutputStream outputStream = new FileOutputStream("D:/test/singleGaurantee.xlsx")) {
			workbook.write(outputStream);
		} finally {
			workbook.close();
		}
	}

	public static List<CBMSData> jsonData() throws Exception {
		File file = new File("C:\\Users\\91970\\Desktop\\RESPONCE.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		// Declaring a string variable
		StringBuilder finalString = new StringBuilder();
		String st;
		// Condition holds true till
		// there is character in a string
		while ((st = br.readLine()) != null) {
			finalString.append(st.trim());
		}
		System.out.println(finalString.toString());
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		FirstJsonData first =gson.fromJson(finalString.toString(), FirstJsonData.class);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		FinalJsonObject first = mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
				.readValue(finalString.toString(), FinalJsonObject.class);
		System.out.println(first);
		return first.getData().getData().getData();
	}
}
