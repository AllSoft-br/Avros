/*
 * Copyright (C) 2015 Allsoft
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.allsoft.avros.formulas;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Classe para conversões de datas
 *
 * @author Luana Nascimento
 */
public class Datas {

    public static String sqlparaString(Date data) {
        Date dataSql = data;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formatada = format.format(dataSql);

        return formatada;
    }
    
    public static String timeParaString(Time hora) {
        Time horaSql = hora;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String formatada = format.format(horaSql);

        return formatada;
    }

    public static Time dateParaTime(java.util.Date data) throws ParseException {
        java.util.Date horaUtil;

        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        horaUtil = format.parse(data.toString());

        java.sql.Time horaSql = new java.sql.Time(horaUtil.getTime());
        
        return horaSql;
    }
    
    /**
     * Pega uma data de um Date, e o horario de um Time e os unifica
     * 
     * @param data
     * @param hora
     * @return data unificada (sql)
     * @throws ParseException 
     */
    public static java.sql.Date unificaData(java.sql.Date data, Time hora) throws ParseException{
        SimpleDateFormat horario = new SimpleDateFormat("HH:mm", Locale.US);
        SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat tudo = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        
        String shora = horario.format(hora);
        String sdia = dia.format(data);
        
        java.util.Date unificado = tudo.parse(sdia + " " + shora);
        java.sql.Date sql = new java.sql.Date(unificado.getTime());
        
        return sql;
    }
}
