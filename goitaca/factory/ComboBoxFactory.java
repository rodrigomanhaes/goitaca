package org.goitaca.factory;

import javax.swing.JComboBox;

public class ComboBoxFactory
{
    public static JComboBox getYesNoComboBox(int selectedIndex)
    {
        JComboBox combo = new JComboBox(new Object[] {"Sim", "Não"});
        combo.setSelectedIndex(selectedIndex);      
        return combo;
    }
    
    public static JComboBox getYesNoComboBox()
    {
        return getYesNoComboBox(-1);
    }
    
    public static JComboBox getGenderCombo(int selectedIndex)
    {
        JComboBox combo = new JComboBox(new Object[] { "Masculino", "Feminino" });
        combo.setSelectedIndex(selectedIndex);
        return combo;
    }
    
    public static JComboBox getGenderCombo()
    {
        return getGenderCombo(-1);
    }
    
    public static JComboBox getMaritalStatusCombo(int selectedIndex)
    {
        JComboBox combo = new JComboBox(new Object[] { "Solteiro", "Casado", "União estável", 
                "Viúvo", "Separado", "Divorciado", "Desquitado" });
        combo.setSelectedIndex(selectedIndex);
        return combo;
    }
    
    public static JComboBox getMaritalStatusCombo()
    {
        return getMaritalStatusCombo(-1);
    }
}
