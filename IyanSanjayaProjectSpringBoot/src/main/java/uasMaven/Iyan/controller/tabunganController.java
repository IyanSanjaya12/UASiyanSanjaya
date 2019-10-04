package uasMaven.Iyan.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uasMaven.Iyan.dao.tabunganDao;
import uasMaven.Iyan.model.tabunganModel;

@RestController
@RequestMapping("/bank")
public class tabunganController {
	@Autowired
	tabunganDao tabungan_Dao;
	
	@GetMapping("/tabunganAll")
	public List<tabunganModel> getAll() {
		return tabungan_Dao.findAll();
	}
	
	@GetMapping("/tabunganBy_id/{id}")
	public ResponseEntity<tabunganModel> getById(@PathVariable(value="id") Long id) {
		tabunganModel tabungan=tabungan_Dao.getFindOneBy_id(id);
		if(tabungan!=null) {
			return ResponseEntity.ok().body(tabungan);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/simpanTabungan")
	public tabunganModel simpanTabungan(@Valid @RequestBody tabunganModel tabungan) {
		return tabungan_Dao.saveTabungan(tabungan);
	}
	
	@GetMapping("/tabunganBy_nik/{nik}")
	public ResponseEntity<List<tabunganModel>> getDataBy_nik(@PathVariable(value="nik") String nik){
		List<tabunganModel> tabng=tabungan_Dao.findDataBy_nik(nik);
		if(tabng!=null) {
			return ResponseEntity.ok().body(tabng);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/updateTabungan/{id}")
	public ResponseEntity<tabunganModel> updateTabungan(@PathVariable(value="id") Long id,@Valid @RequestBody tabunganModel tabungan){
		tabunganModel data=tabungan_Dao.getFindOneBy_id(id);
		if(data!=null) {
			data.setNama(tabungan.getNama());
			data.setSaldo(data.getSaldo()+(data.getKredit()-data.getDebet()));
			data.setKredit(tabungan.getKredit());
			data.setDebet(tabungan.getDebet());
			
			tabunganModel T_result=tabungan_Dao.updateTabungan(data);
			return ResponseEntity.ok().body(T_result);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/deleteTabungan/{id}")
	public ResponseEntity<tabunganModel> deletetabungan(@PathVariable(value="id")Long id){
		tabunganModel tb=tabungan_Dao.getFindOneBy_id(id);
		if(tb==null) {
			return ResponseEntity.notFound().build();
		}else {
			tabungan_Dao.deleteTabungan(id);
			return  ResponseEntity.ok().build();
		}
	}
	
}
