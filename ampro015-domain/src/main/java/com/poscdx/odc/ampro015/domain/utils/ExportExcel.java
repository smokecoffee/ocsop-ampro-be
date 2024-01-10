package com.poscdx.odc.ampro015.domain.utils;

import com.poscdx.odc.ampro015.domain.entity.AssetInfoDto;
import com.poscdx.odc.ampro015.domain.entity.Field;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExportExcel {
    private final XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private final List<AssetInfoDto> assetDtos;

    private final int rowStart = 0;

    public ExportExcel(List<AssetInfoDto> assetDtos) {
        this.assetDtos = assetDtos;
        this.workbook = new XSSFWorkbook();
    }

    private void setBordersToMergedCells(CellRangeAddress rangeAddress) {
        RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, this.sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, this.sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, this.sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, this.sheet);
    }

    private void setMerge(int numRow, int untilRow, int numCol, int untilCol, boolean border) {
        CellRangeAddress cellMerge = new CellRangeAddress(numRow, untilRow, numCol, untilCol);
        this.sheet.addMergedRegion(cellMerge);
        if (border) {
            setBordersToMergedCells(cellMerge);
        }
    }

    private CellStyle createCellStyle(short color, boolean isBold) {
        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        if (isBold) {
            font.setBold(true);
        }
        font.setFontHeight(12);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setWrapText(true);
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private void writeHeaderLine() {
        this.sheet = this.workbook.createSheet("QR Code List");

        for (int i = 0; i < 3; i++) {
            if (i == 2) this.sheet.setColumnWidth(i, 50 * 100);
            else this.sheet.setColumnWidth(i, 50 * 256);
        }

        CellStyle style = this.createCellStyle(IndexedColors.GREY_25_PERCENT.getIndex(), true);
        Row row = this.sheet.createRow(this.rowStart);
        createCell(row, 0, "Owner", style);
        createCell(row, 1, "Asset", style);
        createCell(row, 2, "QR Code", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (columnCount == 1 && !(value instanceof String)) {
            List<Field> tmpList = new ArrayList<>((List<Field>) value);
            String info = tmpList.stream().map(e -> "-" + e.getName() + ": " + e.getValue())
                    .collect(Collectors.joining("\n"));
            cell.setCellValue(info);
        } else if (columnCount == 2 && value instanceof String && !"QR Code".equals(value)) {
            String pngImageURL = (String) value;
            String encodingPrefix = "base64,";
            int contentStartIndex = ((String) value).indexOf(encodingPrefix) + encodingPrefix.length();
            byte[] imageData = Base64.decodeBase64(pngImageURL.substring(contentStartIndex));
            int pictureIdx = this.workbook.addPicture(imageData, this.workbook.PICTURE_TYPE_PNG);

            XSSFDrawing drawing = (XSSFDrawing) this.sheet.createDrawingPatriarch();
            CreationHelper helper = this.workbook.getCreationHelper();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(1);
            anchor.setCol1(2);
            anchor.setRow1(row.getRowNum());
            anchor.setRow2(row.getRowNum() + 1);

            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.getPreferredSize();
        } else {
            if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            } else if (value instanceof Timestamp) {
                Date date = new Date(((Timestamp) value).getTime());
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                cell.setCellValue(f.format(date));
            } else {
                cell.setCellValue((String) value);
            }
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = this.rowStart + 1;
        CellStyle style = this.createCellStyle(IndexedColors.WHITE.getIndex(), false);

        int columnIndex = 2;
        int desiredCellHeight = 100;

        for (AssetInfoDto item : this.assetDtos) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, item.getAsset().getOwner(), style);
            createCell(row, columnCount++, item.getFields(), style);
            createCell(row, columnCount, item.getAsset().getQrcode(), style);

            createCell(row, columnCount, item.getAsset().getQrcode(), style);
            row.getCell(columnIndex).getRow().setHeightInPoints(desiredCellHeight);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        this.workbook.write(outputStream);
        this.workbook.close();
        outputStream.close();
    }
}
