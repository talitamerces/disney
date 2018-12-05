package br.uesb.dovic.servicos;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;



public class ViewService implements Serializable {

    private static final long serialVersionUID = 1L;

    public ViewService() {
    	
    }

    
    public String formatarData(String input) {
    	
    	SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    	try{
    		Date data = formato.parse(input);
    		formato.applyPattern("dd/MM/yyyy");
    		String dataFormatada = formato.format(data);
    		return dataFormatada;
    		}
    	catch (ParseException e){
    		return "Formato de data inválido";
    	}
    	
    }
    
    public List<SelectItem> getDefaultEmptySelectItens() {
        List<SelectItem> defaultEmptySelectItens = new ArrayList<SelectItem>();
        defaultEmptySelectItens.add(new SelectItem(null, "Selecione:"));
        return defaultEmptySelectItens;
    }

    public <T> List<SelectItem> getSelectItensFromCollection(Collection<T> collection, String methodName) {
        List<SelectItem> itens = this.getDefaultEmptySelectItens();
        Method method = null;
        if (collection.size() > 0) {
            try {
                method = collection.iterator().next().getClass().getMethod(methodName, new Class[]{});
            } catch (NoSuchMethodException exception) {
                throw new RuntimeException(exception);
            }
        }
        for (T element : collection) {
            try {
                Object result = method.invoke(element, new Object[]{});
                itens.add(new SelectItem(element, result.toString()));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            } catch (IllegalArgumentException exception) {
                throw new RuntimeException(exception);
            } catch (InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
        return itens;
    }

    public <T> List<SelectItem> getSelectItensFromArray(T[] collection, String methodName) {
        List<SelectItem> itens = this.getDefaultEmptySelectItens();
        Method method = null;
        if (collection.length > 0) {
            try {
                method = collection[0].getClass().getMethod(methodName, new Class[]{});
            } catch (NoSuchMethodException exception) {
                throw new RuntimeException(exception);
            }
        }
        for (T element : collection) {
            try {
                Object result = method.invoke(element, new Object[]{});
                itens.add(new SelectItem(element, result.toString()));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            } catch (IllegalArgumentException exception) {
                throw new RuntimeException(exception);
            } catch (InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
        return itens;
    }
    
    
    public <T> List<SelectItem> getSelectItensFromArrayFiltered(T[] collection, String methodName, List<String> lista) {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        Method method = null;
        if (collection.length > 0) {
            try {
                method = collection[0].getClass().getMethod(methodName, new Class[]{});
            } catch (NoSuchMethodException exception) {
                throw new RuntimeException(exception);
            }
        }
        for (T element : collection) {
            try {
                Object result = method.invoke(element, new Object[]{});
                if (lista.contains(result))
                	itens.add(new SelectItem(element, result.toString()));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            } catch (IllegalArgumentException exception) {
                throw new RuntimeException(exception);
            } catch (InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
        return itens;
    }
    

    public <T> List<SelectItem> getSelectItensFromCollectionWithoutEmpty(Collection<T> collection, String methodName) {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        Method method = null;
        if (collection.size() > 0) {
            try {
                method = collection.iterator().next().getClass().getMethod(methodName, new Class[]{});
            } catch (NoSuchMethodException exception) {
                throw new RuntimeException(exception);
            }
        }
        for (T element : collection) {
            try {
                Object result = method.invoke(element, new Object[]{});
                itens.add(new SelectItem(element, result.toString()));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            } catch (IllegalArgumentException exception) {
                throw new RuntimeException(exception);
            } catch (InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
        return itens;
    }

    public <T> List<SelectItem> getSelectItensFromArrayWithoutEmpty(T[] collection, String methodName) {
        List<SelectItem> itens = new ArrayList<SelectItem>();
        Method method = null;
        if (collection.length > 0) {
            try {
                method = collection[0].getClass().getMethod(methodName, new Class[]{});
            } catch (NoSuchMethodException exception) {
                throw new RuntimeException(exception);
            }
        }
        for (T element : collection) {
            try {
                Object result = method.invoke(element, new Object[]{});
                itens.add(new SelectItem(element, result.toString()));
            } catch (IllegalAccessException exception) {
                throw new RuntimeException(exception);
            } catch (IllegalArgumentException exception) {
                throw new RuntimeException(exception);
            } catch (InvocationTargetException exception) {
                throw new RuntimeException(exception);
            }
        }
        return itens;
    }
}

