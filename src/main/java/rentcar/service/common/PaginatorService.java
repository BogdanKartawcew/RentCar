package rentcar.service.common;

import java.util.ArrayList;

public interface PaginatorService {

    ArrayList<String> getPaginatorTags(String link, int rowsOnPage, int pagesAmount, int pageNumber);

    int pagesAmountCounter(long pageCount, int rowsOnPage);
}
