package solve_system_of_linear_equations;


import org.apache.commons.math3.util.FastMath;

/**
 *
 */
public final class GaussMethodDoubleImpl {
    private final double[][] aMatrix;
    private final double[] bVector;
    private final boolean choseMaxElements;

    /**
     * @param aMatrix          квадратная матрица системы
     * @param bVector          столбец свободных членов
     * @param choseMaxElements - выбор главных элементов
     */
    public GaussMethodDoubleImpl(double[][] aMatrix, double[] bVector, boolean choseMaxElements) {
        this.aMatrix = aMatrix.clone();
        this.bVector = bVector.clone();
        this.choseMaxElements = choseMaxElements;
    }

    // Проверка корректности исходных данных
    private void exceptionsChecking() {
        if (aMatrix.length != bVector.length) {
            throw new IllegalArgumentException("A matrix and B vector has incompatible types");
        }
        if (aMatrix.length != aMatrix[0].length) {
            throw new IllegalArgumentException("A matrix should be of a square");
        }
    }

    public double[] solve() {
        exceptionsChecking();
        // Прямой ход
        if (choseMaxElements) {
            for (int i = 0; i < bVector.length; i++) {
                swapRows(i, findRowWithMaxFirstElement(i));
                divideAllRowElementsByNumber(i, i, aMatrix[i][i]);
                setZeroesUnder(i, i);
            }
        } else {
            for (int i = 0; i < bVector.length; i++) {
                divideAllRowElementsByNumber(i, i, aMatrix[i][i]);
                setZeroesUnder(i, i);
            }
        }
        // обратный ход
        for (int i = bVector.length - 1; i >= 0; i--) {
            setZerosUp(i, i);
        }
        return bVector;
    }

    /**
     * @param column - заданный столбец
     * @return номер строки с максимальным (по модулю) элементом в заданном столбце
     */
    public int findRowWithMaxFirstElement(int column) {
        if (column == bVector.length) {
            return column;
        }
        int maxIndex = column;
        for (int i = column + 1; i < aMatrix.length; i++) {
            if (FastMath.abs(aMatrix[i][column]) > FastMath.abs(aMatrix[maxIndex][column])) maxIndex = i;
        }
        return maxIndex;
    }

    /**
     * Деление строки под номером rowNum начиная с элемента номер colNum на число number
     */
    private void divideAllRowElementsByNumber(int rowNum, int colNum, double number) {
        for (int i = colNum; i < aMatrix.length; i++) {
            aMatrix[rowNum][i] = aMatrix[rowNum][i] / number;
        }
        bVector[rowNum] = bVector[rowNum] / number;
    }

    // "Зануляет" все элементы матрицы под заданным элементом
    public void setZeroesUnder(int rowNum, int colNum) {
        final int aMatrixLength = aMatrix.length;
        if (rowNum == aMatrixLength) {
            return;
        }
        for (int i = rowNum + 1; i < aMatrixLength; i++) {
            double multiplier = aMatrix[i][colNum];
            for (int j = colNum; j < aMatrixLength; j++) {
                aMatrix[i][j] = aMatrix[i][j] - multiplier * aMatrix[rowNum][j];
            }
            //работаем с bVector
            bVector[i] = bVector[i] - bVector[rowNum] * multiplier;
        }
    }

    // "Зануляет" все элементы матрицы над заданным элементом
    public void setZerosUp(int rowNum, int colNum) {
        int matrixLength = aMatrix.length;
        for (int i = rowNum; i > 0; i--) {
            double alpha = aMatrix[i - 1][colNum];
            for (int j = matrixLength - 1; j > colNum - 1; j--) {
                aMatrix[i - 1][j] = aMatrix[i - 1][j] - aMatrix[rowNum][j] * alpha;
            }
            bVector[i - 1] = bVector[i - 1] - bVector[rowNum] * alpha;
        }
    }

    // Меняет строки местами
    public void swapRows(int currentIndex, int maxIndex) {
        double[] tempA = aMatrix[currentIndex];
        double tempB = bVector[currentIndex];
        aMatrix[currentIndex] = aMatrix[maxIndex];
        bVector[currentIndex] = bVector[maxIndex];
        aMatrix[maxIndex] = tempA;
        bVector[maxIndex] = tempB;
    }


}
