package view.backing;


import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.OperationBinding;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.binding.BindingContainer;

public class SaleHeaderDetail_Backing {
    private RichInputText b_quantity;
    private RichInputText b_Rate;
    private RichInputText b_Amount;


    public SaleHeaderDetail_Backing() {
    }
    protected BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
    FacesContext fc = FacesContext.getCurrentInstance();  
    AdfFacesContext context = AdfFacesContext.getCurrentInstance();
    public String saveButtonAction() {
        
        
//        DCIteratorBinding dcIteratorBinding = (DCIteratorBinding) bindings.get("SaleDetailView1Iterator");
//       RowSetIterator rsiRows = dcIteratorBinding.getRowSetIterator();
//        if(rsiRows.getRowCount()>0){
//        }else{
//                
//                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Add Atleast One Item Before Submt.", "");
//                fc.addMessage(null,fm);
//                return null;
//                
//            }
        
                       
        
        
        OperationBinding op = BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("Commit");
        op.execute();
        if(op.getErrors().size()==0){
        FacesContext f = FacesContext.getCurrentInstance();
        f.addMessage(null, new FacesMessage("Record Save Sucessfully."));
       
        }else{
            FacesContext f = FacesContext.getCurrentInstance();
            FacesMessage fm= new FacesMessage("Unable To Save Data.");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            f.addMessage(null, fm);
        }
        return "Welcome";
    }

    public boolean validate_action() {
         
                        
                        
                        DCIteratorBinding iterator = (DCIteratorBinding) bindings.get("SaleDetailView1Iterator");
                        
                        
                        RowSetIterator rsiRows = iterator.getRowSetIterator();
                        Row v_row = rsiRows.getCurrentRow();
                        if(rsiRows.getRowCount()>0){
                            rsiRows.first();
                            
                            for(int i=0;i<rsiRows.getRowCount();i++){
                                
                                if(v_row.getAttribute("SdRate")==null){
                                
                                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Enter Rate!.", "");
                                    fc.addMessage(null,fm);
                                    return false;
                                        
                                }
                                
                                    if(v_row.getAttribute("SdQuantity")==null){
                                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Enter Quantity!.", "");
                                    fc.addMessage(null,fm);
                                    return false;
                                        
                                }
                                
                            }
                                                     
                        }
                        
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return true;
        }
                        
      return true;
      
    }


    public void validateFormField() {
       
        DCIteratorBinding iterator = (DCIteratorBinding) bindings.get("SaleHeaderView1Iterator");
        ViewObject vo = iterator.getViewObject();
        Row v_row = vo.getCurrentRow();
        
      
            if ( v_row.getAttribute("SaDate")==null || v_row.getAttribute("SaDate").toString().equals("")) {
                System.err.println("inside call---");
                        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Select Sale Order Date.", "");
                        fc.addMessage(null,fm);
                        return ;
            } 
        
      
    }

    public void onQuantityChangeAction(ValueChangeEvent valueChangeEvent) {
        
         BigDecimal amount= BigDecimal.ZERO;
        
        try {
            DCIteratorBinding iterator =
                (DCIteratorBinding) bindings.get("SaleDetailView1Iterator");
            Row v_row = iterator.getCurrentRow();
            if (valueChangeEvent.getNewValue()!=null && !valueChangeEvent.getNewValue().toString().equals("")) {
                if (v_row.getAttribute("SdRate")!=null && !v_row.getAttribute("SdRate").toString().equals("")) {
                   amount =  new BigDecimal(v_row.getAttribute("SdRate").toString()).multiply(new BigDecimal(valueChangeEvent.getNewValue().toString()));
                }
                
            }
            
            v_row.setAttribute("SdValue", amount);
            b_Amount.setValue(amount);
           
            context.addPartialTarget(b_Amount);
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
    }

    public void onRateChangeAction(ValueChangeEvent valueChangeEvent) {
        BigDecimal amount= BigDecimal.ZERO;
        
        try {
           DCIteratorBinding iterator =
               (DCIteratorBinding) bindings.get("SaleDetailView1Iterator");
           Row v_row = iterator.getCurrentRow();
           if (valueChangeEvent.getNewValue()!=null && !valueChangeEvent.getNewValue().toString().equals("")) {
               if (v_row.getAttribute("SdQuantity")!=null && !v_row.getAttribute("SdQuantity").toString().equals("")) {
                  amount =  new BigDecimal(v_row.getAttribute("SdQuantity").toString()).multiply(new BigDecimal(valueChangeEvent.getNewValue().toString()));
               }
               
           }
           
           v_row.setAttribute("SdValue", amount);
           b_Amount.setValue(amount);
          
           context.addPartialTarget(b_Amount);
           
        } catch (Exception e) {
           
           e.printStackTrace();
        }
    }

    public void setB_quantity(RichInputText b_quantity) {
        this.b_quantity = b_quantity;
    }

    public RichInputText getB_quantity() {
        return b_quantity;
    }

    public void setB_Rate(RichInputText b_Rate) {
        this.b_Rate = b_Rate;
    }

    public RichInputText getB_Rate() {
        return b_Rate;
    }


    public void setB_Amount(RichInputText b_Amount) {
        this.b_Amount = b_Amount;
    }

    public RichInputText getB_Amount() {
        return b_Amount;
    }

    public String b2_action() {
        DCIteratorBinding iterator = (DCIteratorBinding) bindings.get("SaleHeaderView1Iterator");
        ViewObject vo = iterator.getViewObject();
        Row v_row = vo.getCurrentRow();
        
        
            if ( v_row.getAttribute("SaDate")==null || v_row.getAttribute("SaDate").toString().equals("")) {
                System.err.println("inside call---");
                        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Select Sale Order Date!", "");
                        fc.addMessage(null,fm);
                        return null;
            }
            
        if ( v_row.getAttribute("SaPartyId")==null || v_row.getAttribute("SaPartyId").toString().equals("")) {
           
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Select Party!", "");
                    fc.addMessage(null,fm);
                    return null;
        }
        
        return "goToDetail";
    }
}
