package running;

public class LatexOutputUtil {
    public static final String headOfTable = "\\begin{table}[H]\n" +
            "        \\centering\n" +
            "        \\begin{tabular}{|l|l|l|l|}\n" +
            "            \\hline\n" +
            "            x      & \\begin{tabular}[c]{@{}l@{}}Значение\\\\ исходной\\\\ функции \\end{tabular}& \\begin{tabular}[c]{@{}l@{}}Значение \\\\ аппроксимирующей \\\\функции\\end{tabular} & Модуль разности    \\\\\n" +
            "            \\hline";
    public static final String endOfTable = "\\end{tabular}\n";
}
