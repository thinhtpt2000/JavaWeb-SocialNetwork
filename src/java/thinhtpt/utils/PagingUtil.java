/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThinhTPT
 */
public class PagingUtil implements Serializable {

    public static List<Integer> getListNumPage(int currentPage, int numOfPage) {
        List<Integer> list = new ArrayList<>();
        if (numOfPage >= 3) {
            if (currentPage <= 2) {
                list.add(1);
                list.add(2);
                list.add(3);
            } else {
                if (currentPage < numOfPage) {
                    list.add(currentPage - 1);
                    list.add(currentPage);
                    list.add(currentPage + 1);
                } else {
                    list.add(currentPage - 2);
                    list.add(currentPage - 1);
                    list.add(currentPage);
                }
            }
        } else if (numOfPage == 2) {
            list.add(1);
            list.add(2);
        } else {
            list.add(1);
        }
        return list;
    }
}
