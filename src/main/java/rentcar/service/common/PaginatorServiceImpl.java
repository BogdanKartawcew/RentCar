package rentcar.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("paginatorService")
@Transactional
public class PaginatorServiceImpl implements PaginatorService {
    @Override
    public ArrayList<String> getPaginatorTags(String link, int rowsOnPage, int pagesAmount, int activePageNumber) {
        //whole tag for active page is: <li class="active"><a href="...link..."></a></li>
        //the whole tag who rest pages is: <li><a href="...link..."></a></li>

        String startActive = "<li class=\"active\"><a href=\"" + link;
        String startUsual = "<li><a href=\"" + link;
        String middle = "per" + rowsOnPage + "\">";
        String end = "</a></li>";
        StringBuilder tag = new StringBuilder();
        ArrayList<String> paginatorTags = new ArrayList<>();
        for (int usualPageNumber = 1; usualPageNumber <= pagesAmount; usualPageNumber++) {
            if (usualPageNumber == activePageNumber) {
                tag.append(startActive);
            } else {
                tag.append(startUsual);
            }
            tag.append(usualPageNumber);
            tag.append(middle);
            tag.append(usualPageNumber);
            tag.append(end);
            paginatorTags.add(tag.toString());
            tag.setLength(0);
        }
        return paginatorTags;
    }

    @Override
    public int pagesAmountCounter(long pageCount, int rowsOnPage) {
        return (int) Math.ceil((double) pageCount / rowsOnPage);
    }
}
