package com.bqsolo.framework.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.FastHashMap;


/** 
* @ClassName: ListUtil 
* @Description: TODO
* @author xuefeng.gao
* @date 2014-11-2 
* 
* @param <T> 
*/ 

public class ListUtil<T> {



    private static final int MAX_RECORDS_NUM = 999;

    private ListUtil() {

    }

    /**
     * Merge the list of data to main list by list of key
     *
     * @param lstMain Main list
     * @param lstData List of data. Every item is also a list.
     * @param lstKey  List of key
     */
    
	public static void mergeData(List lstMain, List lstData, List lstKey) {
        if (lstMain == null || lstData == null || lstData.size() == 0 || lstKey == null || lstKey.size() == 0) {
            return;
        }

        for (int index1 = 0, n = lstMain.size(); index1 < n; index1++) {
            Map mapMain = (Map) lstMain.get(index1);
            Map keyValue = new HashMap();

            for (int index2 = 0, m = lstKey.size(); index2 < m; index2++) {
                Object key = lstKey.get(index2);
                keyValue.put(key, mapMain.get(key));
            }

            for (int index2 = 0, m = lstData.size(); index2 < m; index2++) {
                List lstChild = (List) lstData.get(index2);
                for (int index3 = 0, s = lstChild.size(); index3 < s; index3++) {
                    Map mapChild = (Map) lstChild.get(index3);

                    boolean flag = true;
                    for (int index4 = 0, t = lstKey.size(); index4 < t; index4++) {
                        Object key = lstKey.get(index4);
                        Object valueFromMain = keyValue.get(key);
                        Object valueFromChild = mapChild.get(key);

                        if ((valueFromMain != null && !valueFromMain.equals(valueFromChild))
                            || (valueFromMain == null && valueFromChild != null)) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        mapMain.putAll(mapChild);
                        break;
                    }
                }
            }
        }

        return;
    }

    public static List joinRecords(List first, List second, String key) {
        if (first != null && second != null) {
            for (Iterator iterator1 = first.iterator(); iterator1.hasNext(); ) {
                Map record1 = (Map) iterator1.next();
                for (Iterator iterator2 = second.iterator(); iterator2.hasNext(); ) {
                    Map record2 = (Map) iterator2.next();
                    if (record2.get(key) != null && record2.get(key).equals(record1.get(key))) {
                        record1.putAll(record2);
                        break;
                    }
                }
            }
        }
        return first;
    }

    public static boolean isEmpty(List data) {
        boolean isEmpty = true;
        if (data != null && data.size() > 0) {
            isEmpty = false;
        }
        return isEmpty;
    }

    public static boolean isNotEmpty(List data) {
        return !isEmpty(data);
    }

    public static List splitRecords(List lst) {
        return splitRecords(lst, MAX_RECORDS_NUM);
    }

    public static List splitRecords(List lst, int recordNum) {
        if (!isEmpty(lst)) {
            @SuppressWarnings("rawtypes")
			List newList = new ArrayList();
            int div = lst.size() / recordNum;
            int residue = lst.size() % recordNum;
            if (lst.size() < recordNum) {
                newList.add(lst);
            } else {
                int i = 0;
                for (i = 0; i < div; i++) {
                    newList.add(lst.subList(i * recordNum, (i + 1) * recordNum));
                }
                newList.add(lst.subList(i * recordNum, i * recordNum + residue));
            }
            return newList;
        }
        return null;
    }

    public static List splitRecordsAsPage(List lst, int recordNumPerPage, String recordKey, String pageNumKey) {
        if (!isEmpty(lst)) {
            List newList = new ArrayList();
            int div = lst.size() / recordNumPerPage;
            int residue = lst.size() % recordNumPerPage;
            if (lst.size() <= recordNumPerPage) {
                List dataLst = new ArrayList();
                Map dataMap = new FastHashMap();
                dataMap.put(pageNumKey, new Integer(1));
                dataMap.put(recordKey, lst);
                dataLst.add(dataMap);
                newList.add(dataLst);
            } else {
                int i;
                for (i = 0; i <= div; i++) {
                    List subdataLst = new ArrayList();
                    for (int j = 0; j < lst.size(); j++) {
                        Map dataItem = (Map) lst.get(j);
                        if (j / recordNumPerPage == i) {
                            subdataLst.add(dataItem);
                        }
                    }
                    List dataLst = new ArrayList();
                    Map dataMap = new FastHashMap();
                    dataMap.put(pageNumKey, new Integer(residue > 0 ? div + 1 : div));
                    dataMap.put(recordKey, subdataLst);
                    dataLst.add(dataMap);
                    newList.add(dataLst);
                }
            }
            return newList;
        }
        return null;
    }

    /**
     * convertToSimpleList
     *
     * @param lst
     * @param key
     * @return List
     *         convert a List with Map to a List with Object,and the 'key' is key of item of Map
     */
    public static List convertToSimpleList(List lst, String key) {
        if (!isEmpty(lst)) {
            List newList = new ArrayList();
            for (int i = 0; i < lst.size(); i++) {
                if (lst.get(i) instanceof Map) {
                    Object obj = ((Map) lst.get(i)).get(key);
                    newList.add(obj);
                } else {
                    return null;
                }
            }
            return newList;
        }
        return null;
    }

    public static List mergeRecords(List first, List second, String key, String mergekey, String splitSyb) {
        if (first != null && second != null) {
            for (int i = 0; i < first.size(); i++) {
                Map record1 = (Map) first.get(i);
                for (Iterator iterator1 = second.iterator(); iterator1.hasNext(); ) {
                    Map record2 = (Map) iterator1.next();
                    if (record2.get(key) != null && record2.get(key).equals(record1.get(key))) {
                        Object o = record1.get(mergekey);
                        if (o != null) {
                            record1.put(mergekey, o.toString() + splitSyb + record2.get(mergekey));
                        } else {
                            record1.put(mergekey, record2.get(mergekey));
                        }
                    }
                }
                first.set(i, record1);
            }
        }
        return first;
    }

    /**
     * Sort List by value in key,required List contains Map
     *
     * @param lst
     * @param key
     * @return List
     */
    public static List sortList(List lst, String key) {
        if (!isEmpty(lst)) {
            for (int i = lst.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    String value1 = (String) ((Map) lst.get(j)).get(key);
                    String value2 = (String) ((Map) lst.get(j + 1)).get(key);
                    if (value1 != null && value1.compareTo(value2) > 0) {
                        Map tmp = (Map) lst.get(j);
                        lst.set(j, (Map) lst.get(j + 1));
                        lst.set(j + 1, tmp);
                    }
                }
            }
            return lst;
        }
        return null;
    }



    /**
     * Sort List by value in key,required List contains Mao
     *
     * @param lst
     * @param key
     * @return List
     */
    public static List sortListByDate(List lst, String key) {
        if (!isEmpty(lst)) {
            for (int i = lst.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    Date value1 = (Date) ((Map) lst.get(j)).get(key);
                    Date value2 = (Date) ((Map) lst.get(j + 1)).get(key);
                    if (value1.compareTo(value2) > 0) {
                        Map tmp = (Map) lst.get(j);
                        lst.set(j, (Map) lst.get(j + 1));
                        lst.set(j + 1, tmp);
                    }
                }
            }
            return lst;
        }
        return null;
    }

    /**
     * Sort List by value
     *
     * @param lst
     * @return List
     */
    public static List sortList(List lst) {
        if (!isEmpty(lst)) {
            for (int i = lst.size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    String value1 = (String) lst.get(j);
                    String value2 = (String) lst.get(j + 1);
                    if (value1.compareTo(value2) > 0) {
                        String tmp = (String) lst.get(j);
                        lst.set(j, (String) lst.get(j + 1));
                        lst.set(j + 1, tmp);
                    }
                }
            }
            return lst;
        }
        return null;
    }

    public static List makeStringToList(String ownerids) {
        ownerids = ownerids.replaceAll("\'", "");
        List lstValues = Arrays.asList(ownerids.split(","));
        return lstValues;
    }



    public static List splitRecordsAsPage(List input, int recordNumPerPage) {
        List output = new ArrayList();

        if (!isEmpty(input)) {
            int size = input.size();
            int div = size / recordNumPerPage;
            int residue = size % recordNumPerPage;

            if (div > 0) {
                for (int i = 0; i < div; i++) {
                    List tmp = new ArrayList();
                    for (int j = i * recordNumPerPage; j < (i + 1) * recordNumPerPage; j++) {
                        tmp.add(input.get(j));
                    }
                    output.add(tmp);
                }
            }

            if (residue > 0) {
                List tmp = new ArrayList();
                for (int m = div * recordNumPerPage; m < size; m++) {
                    tmp.add(input.get(m));
                }
                output.add(tmp);
            }
        }

        return output;
    }





    public static List getDeepCloneListForMapItem(List lst) {
        List resultList = new ArrayList();
        if (!isEmpty(lst)) {
            for (int i = 0; i < lst.size(); i++) {
                Object item = lst.get(i);
                if (item instanceof Map) {
                    Map itemClone = new FastHashMap();
                    itemClone.putAll((Map) item);
                    resultList.add(itemClone);
                } else {
                    return null;
                }
            }
            return resultList;
        }
        return null;
    }

    public static boolean isListInstance(Object obj) {
        return List.class.isInstance(obj);
    }






    public static String listToStringUsedBySQL(List argList, String split) {
        StringBuffer sb = new StringBuffer();
        if (!isEmpty(argList)) {
            for (int i = 0; i < argList.size(); i++) {
                sb.append("'" + argList.get(i) + "'");
                if (i != argList.size() - 1) {
                    sb.append(split);
                }
            }
        }
        return sb.toString();
    }


    public static boolean isContainKey(List list, Object key) {
        boolean isContainKey = false;
        Map map = null;
        if (!isEmpty(list)) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                map = (Map) iterator.next();
                if (map.containsKey(key)) {
                    isContainKey = true;
                    break;
                }
            }
        }
        return isContainKey;
    }




    public static List pickValues(List lst, Object key) {
        List values = null;
        if (isNotEmpty(lst)) {
            values = new ArrayList();
            for (int i = 0; i < lst.size(); i++) {
                Map item = (Map) lst.get(i);
                Object value =item.get(key);
                if (value != null) {
                    values.add(value);
                }
            }
        }
        return values;
    }


    public static void resetValues(List lst, Object key, Object newValue) {
        if (isNotEmpty(lst)) {
            for (int i = 0; i < lst.size(); i++) {
                Map item = (Map) lst.get(i);
                item.put(key, newValue);
            }
        }
    }

    public static List createBlankList(int size) {
        List blankList = new ArrayList();
        if (size >= 0) {
            for (int i = 0; i < size; i++) {
                blankList.add("");
            }
        }
        return blankList;
    }

    public static void syncSize(List srcList1, List srcList2, Object element) {
        srcList1 = nulltoBlank(srcList1);
        srcList2 = nulltoBlank(srcList2);
        if (srcList1 != null && srcList2 != null) {
            if (srcList1.size() > srcList2.size()) {
                int size = srcList1.size() - srcList2.size();
                for (int i = 0; i < size; i++) {
                    srcList2.add(element);
                }
            } else if (srcList2.size() > srcList1.size()) {
                int size = srcList2.size() - srcList1.size();
                for (int i = 0; i < size; i++) {
                    srcList1.add(element);
                }
            }
        }
    }

    public static List nulltoBlank(List list) {
        if (list == null) {
            list = new ArrayList();
        }
        return list;
    }

    public static boolean isEmptyBufferList(List list) {
        boolean isEmptyBufferList = false;
        if (isEmpty(list)) {
            isEmptyBufferList = true;
        } else {
            boolean isBreak = false;
            for (int i = 0; i < list.size(); i++) {
                List bufferList = (List) list.get(i);
                if (!isEmpty(bufferList)) {
                    isBreak = true;
                    break;
                }
            }
            if (!isBreak) {
                isEmptyBufferList = true;
            }
        }
        return isEmptyBufferList;
    }

    public static Object get(List lst, int i) {
        if (isEmpty(lst)) return null;
        if (lst.size() <= i) return null;
        return lst.get(i);
    }

    public static Object set(List lst, int i, Object elemnt) {
        if (isEmpty(lst)) return null;
        if (lst.size() <= i) return null;
        return lst.set(i, elemnt);
    }

    public static boolean addAll(List input, List added) {
        if (isEmpty(added)) return false;
        else
            return input.addAll(added);
    }

    public static List distinctStrItem(List lstStr, boolean ignoreCase) {
        if (isNotEmpty(lstStr)) {
            int deleteCount = 1;
            if (ignoreCase) {
                for (int i = lstStr.size() - 1; i > 0; i = i - deleteCount) {
                    String exist = (String) lstStr.get(i);
                    deleteCount = 1;
                    for (int j = i - 1; j >= 0 && j < lstStr.size(); j--) {
                        String duplicate = (String) lstStr.get(j);
                        if (duplicate != null && duplicate.equalsIgnoreCase(exist)) {
                            lstStr.remove(j);
                            deleteCount++;
                        }
                    }
                }
            } else {
                for (int i = lstStr.size() - 1; i > 0; i = i - deleteCount) {
                    String exist = (String) lstStr.get(i);
                    deleteCount = 1;
                    for (int j = i - 1; j >= 0 && j < lstStr.size(); j--) {
                        String duplicate = (String) lstStr.get(j);
                        if (duplicate != null && duplicate.equals(exist)) {
                            lstStr.remove(j);
                            deleteCount++;
                        }
                    }
                }
            }
        }
        return lstStr;
    }

    public static boolean containsObject(List source, Object index) {
        boolean contain = false;
        if (isNotEmpty(source)) {
            for (int i = 0; i < source.size(); i++) {
                Object item = source.get(i);
                if (item != null && item.equals(index)) {
                    contain = true;
                    break;
                }
            }
        }
        return contain;
    }


    /**
     * Warning: This method is only applied to Map-List Structure
     * 1. Any non-List/Map element will be compared by object1.equals(object2)
     * 2. Null compared Null is defined as true.
     */
    public static boolean areEqual(List list1, List list2) {
        if (list1 == null && list2 == null) {
            return true;
        } else if (list1 != null && list2 == null) {
            return false;
        } else if (list1 == null && list2 != null) {
            return false;
        } else {
            if (list1.size() == list2.size()) {
                for (int i = 0; i < list1.size(); i++) {
                    Object obj1 = list1.get(i);
                    Object obj2 = list2.get(i);
                    if (obj1 == null && obj2 == null) {
                        continue;
                    } else if (obj1 != null && obj2 == null) {
                        return false;
                    } else if (obj1 == null && obj2 != null) {
                        return false;
                    } else {
                        if ((obj1 instanceof List) && (obj2 instanceof List)) {
                            areEqual((List) obj1, (List) obj2);
                        } else if ((obj1 instanceof Map) && (obj2 instanceof Map)) {
                            areEqual((Map) obj1, (Map) obj2);
                        } else {
                            if (obj1.equals(obj2)) {
                                continue;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean areEqual(Map map1, Map map2) {
        if (map1 == null && map2 == null) {
            return true;
        } else if (map1 != null && map2 == null) {
            return false;
        } else if (map1 == null && map2 != null) {
            return false;
        } else {
            if (map1.size() == map2.size()) {
                for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext(); ) {
                    Object key1 = iterator.next();
                    if (map2.containsKey(key1)) {
                        Object value1 = map1.get(key1);
                        Object value2 = map2.get(key1); //key for 2 maps must be same
                        if (value1 == null && value2 == null) {
                            continue;
                        } else if (value1 != null && value2 == null) {
                            return false;
                        } else if (value1 == null && value2 != null) {
                            return false;
                        } else {
                            if ((value1 instanceof List) && (value2 instanceof List)) {
                                areEqual((List) value1, (List) value2);
                            } else if ((value1 instanceof Map) && (value2 instanceof Map)) {
                                areEqual((Map) value1, (Map) value2);
                            } else {
                                if (value1.equals(value2)) {
                                    continue;
                                } else {
                                    return false;
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static List convertArrayToList(Object[] array) {
        if (array != null) {
            List list = new ArrayList();
            for (int i = 0; i < array.length; i++) {
                list.add(array[i]);
            }
            return list;
        } else {
            return null;
        }
    }

    public static <T> T[] convertListToArray(List<T> list) {
        if (list != null && !list.isEmpty()) {
            T t = list.get(0);
            if (t != null) {
                T[] array = (T[]) Array.newInstance(t.getClass(), list.size());
                for (int i = 0; i < list.size(); i++) {
                    T t1 = list.get(i);
                    array[i] = t1;
                }
                return array;
            }
        }
        return null;
    }



    private static void sort(List lst, int index) {
        Map tmp = (Map) lst.get(index);
        lst.set(index, (Map) lst.get(index + 1));
        lst.set(index + 1, tmp);
    }

    public static List filterList(List<Map> lst, Object filterKey, Object filterValue) {
        if (lst == null) {
            return null;
        }
        List result = new ArrayList();
        for (Iterator<Map> mapIterator = lst.iterator(); mapIterator.hasNext(); ) {
            Map map = mapIterator.next();
            if (map.containsKey(filterKey)) {
                Object value = map.get(filterKey);
                if (filterValue == null && value == null || filterValue.equals(value)) {
                    result.add(map);
                }
            }
        }
        return result;
    }

    /**
     * return the filetered list
     *
     * @param lst
     * @param filterKey
     * @param filterValue
     * @return
     */
    public static List realFilterListByKey(List<Map> lst, Object filterKey, Object filterValue) {
        if (lst == null) {
            return null;
        }
        List holdList = new ArrayList();
        for (Iterator<Map> mapIterator = lst.iterator(); mapIterator.hasNext(); ) {
            Map map = mapIterator.next();
            if (map.containsKey(filterKey)) {
                Object value = map.get(filterKey);
                if (!(filterValue == null && value == null || filterValue.equals(value))) {
                    holdList.add(map);
                }
            }
        }
        return holdList;
    }

    public static boolean existInList(List<Map> lst, Object filterKey, Object filterValue) {
        if (lst == null) {
            return false;
        }
        for (Iterator<Map> mapIterator = lst.iterator(); mapIterator.hasNext(); ) {
            Map map = mapIterator.next();
            if (map.containsKey(filterKey)) {
                Object value = map.get(filterKey);
                if (filterValue == null && value == null || filterValue.equals(value)) {
                    return true;
                }
            }
        }

        return false;

    }

    public static void resetList(List list) {
        if (list == null) {
            list = new ArrayList();
        } else {
            list.clear();
        }
    }

    public static List minusList(List subtrahend, List minuend) {
        if (!isEmpty(subtrahend) && null != minuend && subtrahend.size() >= minuend.size()) {
            for (int i = subtrahend.size() - 1; i >= 0; i--) {
                Object subtranhendItem = subtrahend.get(i);
                for (int j = minuend.size() - 1; j >= 0; j--) {
                    Object minuedItem = minuend.get(j);
                    if (subtranhendItem != null && minuedItem != null &&
                        minuedItem.equals(subtranhendItem)) {
                        subtrahend.remove(i);
                        break;
                    }
                }
            }
        }
        return subtrahend;
    }


   



    /**
     * Revmove the Duplicate record from list
     *
     * @param list
     * @return list
     */
    public static List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * @param fieldName table name field name
     * @param lstValues value list
     * @return the condition SQL,
     */
    public static String listSingleFieldToStringUsedBySQL(List lstValues, String fieldName) {
        StringBuffer sb = new StringBuffer();
        if (ListUtil.isNotEmpty(lstValues)) {
            sb.append(" (");
            int size = lstValues.size();
            int remain = size % 1000;
            int times = size / 1000;
            if (times == 0) {
                sb.append(fieldName + " in (");
                for (int j = 0; j < size; j++) {
                    sb.append("'" + lstValues.get(j) + "'");
                    if (j != size - 1) {
                        sb.append(",");
                    }
                }
                sb.append(") ");
            } else if (times > 0) {
                for (int i = 0; i < times; i++) {
                    if (i != 0) {
                        sb.append(" or ");
                    }
                    sb.append(fieldName + " in (");
                    for (int j = 1000 * i; j < (i + 1) * 1000; j++) {
                        sb.append("'" + lstValues.get(j) + "'");
                        if (j != (i + 1) * 1000 - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append(") ");
                }
                if (remain > 0) {
                    sb.append(" or " + fieldName + " in (");
                    for (int j = times * 1000; j < size; j++) {
                        sb.append("'" + lstValues.get(j) + "'");
                        if (j != size - 1) {
                            sb.append(",");
                        }
                    }
                    sb.append(") ");
                }
            }
            sb.append(") ");

        }
        return sb.toString();
    }

    public static void reverseList(List list) {
        if (isNotEmpty(list) && list.size() > 1) {
            int size = list.size();
            for (int i = 0; i < size / 2; i++) {
                int backwardIndex = size - i - 1;
                Object ahead = list.get(i);
                Object backward = list.get(backwardIndex);
                list.set(i, backward);
                list.set(backwardIndex, ahead);
            }
        }
    }
    public static Object getFristRecord(List  list){
    	if(isNotEmpty(list)){
    		return list.get(0);
    	}else{
    		return null;
    	}
    }


}