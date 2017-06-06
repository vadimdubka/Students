package web.forms;

import java.util.Collection;

/*JSP 2.0 и выше используется так называемы «Язык выражений» (ЯВ) или Expression Language (EL) — основная его идея в том, чтобы не писать страшные конструкции типа
<%= request.getParameter(«parameter») %>
ЯВ имеет упрощенный синтаксис обращения к полям объектов, которые находятся в JSP (это как раз наша структура, которая содержит много данных). Писать постоянно формулы и get/set методы не очень интересно и поэтому был сделан ЯВ. В нем обращение к данным гораздо приятнее на вид.
Очень важно учесть, что ЯВ требует правильного именования полей и наличия методов set/get. Чуть ниже приведен код нашей структуры и там можно увидеть как именовать поля и методы доступа к ним.
можем использовать в качестве ${}*/

public class MainFrameForm {
    private int year;
    private int groupId;
    private Collection groups;
    private Collection students;

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroups(Collection groups) {
        this.groups = groups;
    }

    public Collection getGroups() {
        return groups;
    }

    public void setStudents(Collection students) {
        this.students = students;
    }

    public Collection getStudents() {
        return students;
    }
}