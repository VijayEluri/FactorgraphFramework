package graphEditor.factorgraph;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class DataFileReader {

	String file = null;
	String[] header = null;;
	double[][] values = null;

	public DataFileReader(String file) throws Exception {
		this.file = file;
		ICsvListReader inFile = new CsvListReader(new FileReader(file),
				CsvPreference.EXCEL_PREFERENCE);
		try {
			header = inFile.getCSVHeader(true);
			int column = header.length;
			int line = 0;
			System.out.println("header:" + Arrays.toString(header));
			List<String> data = null;
			List<double[]> dataArrays = new ArrayList<double[]>();
			while ((data = inFile.read()) != null) {
				if (inFile.length() == header.length) {
					double[] array = new double[inFile.length()];
					for (int i = 0; i < data.size(); i++) {
						array[i] = Double.valueOf(data.get(i)).doubleValue();
					}
					dataArrays.add(array);
				} else {
					throw new Exception("error with line:"
							+ inFile.getLineNumber());
				}

				line++;
			}
			values = new double[line][column];
			for (int i = 0; i < line; i++) {
				values[i] = dataArrays.get(i);
			}

		} finally {
			inFile.close();
		}

	}

	public String[] getHeader() {
		return header;
	}

	public double[][] getValues() {
		return values;
	}
}
