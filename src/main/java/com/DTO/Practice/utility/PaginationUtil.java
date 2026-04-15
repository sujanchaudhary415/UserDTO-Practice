package com.DTO.Practice.utility;

import com.DTO.Practice.wrapper.PagedResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationUtil {
    public static <T> PagedResponse<T> build(Page<?> page, List<T> content) {
        return new PagedResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
