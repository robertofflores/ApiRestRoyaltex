package com.tech.ws.rest.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import com.tech.ws.rest.vo.VOBono;
import com.tech.ws.rest.vo.VOCcosto;
import com.tech.ws.rest.vo.VOCinope;
import com.tech.ws.rest.vo.VOCliente;
import com.tech.ws.rest.vo.VODatosMP;
import com.tech.ws.rest.vo.VOEmpresa;
import com.tech.ws.rest.vo.VOOpcion;
import com.tech.ws.rest.vo.VOProducto;
import com.tech.ws.rest.vo.VOSucursal;
import com.tech.ws.rest.vo.VOUsuario;
import com.tech.ws.rest.vo.VOModulo;
import com.tech.ws.rest.vo.VOVentasDist;
import com.tech.ws.rest.vo.VoDconti;

@Path("/JavaMobil")
public class ServiceLoggin {
	public String res = "X";
	
	ConnectionJDBC con = new ConnectionJDBC();
	
	@POST
	@Path("/registraUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean registrar(VOUsuario vo) {
		boolean registrar = false;
		
		Statement stm= null;
		Connection co=null;
		
		String sql="INSERT INTO mblusuario (nombre_usuario,usuario,password,estado,usua_num_cedula) values ('"+vo.getNombre()+"','"+vo.getUsuario()+"','"+vo.getPassword()+"','"+vo.getEstado()+"','"+vo.getCedula()+"')";
			  
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			registrar = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, Ingresar Datos Distribuidor!");
			e.printStackTrace();
		}
		return registrar;
	}
	@POST
	@Path("/validarUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOUsuario> validarUsuario(VOUsuario vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="SELECT * FROM mblusuario WHERE usua_num_cedula = '" + vo.getCedula() + "'";
		
		List<VOUsuario> listaUsuario= new ArrayList<VOUsuario>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOUsuario voi = new VOUsuario();
				voi.setNombre(rs.getString(2));
				voi.setUsuario(rs.getString(3));
				voi.setPassword(rs.getString(4));
				voi.setEstado(rs.getString(6));
				voi.setCedula(rs.getString(8));
				if(voi.getPassword().equals(vo.getPassword())){
					voi.setUservalido(true);
				}
				listaUsuario.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaUsuario;
	}
	@GET
	@Path("/obtenerUsuario")
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOUsuario> obtenerUsuario(){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="SELECT * FROM mblusuario";
		
		List<VOUsuario> listaUsuario= new ArrayList<VOUsuario>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOUsuario vo = new VOUsuario();
				vo.setNombre(rs.getString(2));
				vo.setUsuario(rs.getString(3));
				vo.setPassword(rs.getString(4));
				vo.setEstado(rs.getString(6));
				listaUsuario.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		
		return listaUsuario;
	}
	class Resultado{
		private int existe;

		public int getExiste() {
			return existe;
		}

		public void setExiste(int existe) {
			this.existe = existe;
		}
	}
	
	
	@POST
	@Path("/existeDistribuidor")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Resultado> existeDistribuidor(VOVentasDist vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		//boolean resultado = false;
		
		String sql="SELECT count(*) existe FROM mbldadis where dadis_cod_empr=1 and dadis_cod_clpv = "+ vo.getCodCliente() + " and dadis_cod_ciud = "+ vo.getCodCiudad() + " and dadis_fech_dadis = date('" + vo.getFecha()+ "') and local = '"+vo.getLocal()+"'";
		System.out.println(sql);
		List<Resultado> listaDist= new ArrayList<Resultado>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				Resultado voi = new Resultado();
				voi.setExiste(rs.getInt(1));
				
				listaDist.add(voi);
			}

			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método Dist");
			
			e.printStackTrace();
		}
		
		return listaDist;
	
	}
	
	@POST
	@Path("/existeCodigo")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Resultado> existeCodigo(VOVentasDist vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		//boolean resultado = false;
		
		String sql="select count(*) existe from saeprbo where prbo_cod_empr=1 and prbo_cod_bode=1 and prbo_cod_prod = '" + vo.getCodigo() + "'";
		System.out.println(sql);
		List<Resultado> listaDist= new ArrayList<Resultado>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				Resultado voi = new Resultado();
				voi.setExiste(rs.getInt(1));
				
				listaDist.add(voi);
			}

			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método Dist");
			
			e.printStackTrace();
		}
		
		return listaDist;
	
	}
	
	@POST
	@Path("/eliminarDistribuidor")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean eliminarDistribuidor(VOVentasDist vo){
		Connection co = null;
		Statement stm = null;
		boolean resultado = false;
		
		String sql="DELETE FROM mbldadis where dadis_cod_empr=1 and dadis_fech_dadis = date('" + vo.getFecha()+ "') and dadis_cod_clpv = "+ vo.getCodCliente() + " and dadis_cod_ciud = " + vo.getCodCiudad() + " and local = '" +vo.getLocal() + "'";
		System.out.println(sql);
	
		try {			
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			resultado = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método Dist");
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}
		
		return resultado;
	
	}
	@POST
	@Path("/obtenerModulo")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOModulo> obtenerModulo(VOUsuario vou){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="SELECT modu_des_modu,modu_cod_modu FROM saemodu where modu_cod_modu in (select opci_cod_modu FROM mblopci WHERE opci_cod_opci in (select acceso_codigo_opci from mblacceso where acceso_codigo_usua in (select codigo_usuario from mblusuario where usuario = '"+vou.getUsuario()+"')))";
		System.out.print(sql);
		List<VOModulo> listaModulo= new ArrayList<VOModulo>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOModulo vo = new VOModulo();
				vo.setNombre_modulo(rs.getString(1));
				vo.setCod_modulo(rs.getInt(2));
				listaModulo.add(vo);
				
			}
			stm.close();
			rs.close();
			co.close();
			
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		return listaModulo;
	}
	@POST
	@Path("/obtenerOpciones")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOOpcion> obtenerOpcion(VOUsuario voi){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="SELECT * FROM mblopci where opci_cod_opci in (select acceso_codigo_opci from mblacceso where acceso_codigo_usua in (select codigo_usuario from mblusuario where usuario='"+voi.getUsuario()+"'))";
		
		List<VOOpcion> listaOpcion= new ArrayList<VOOpcion>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOOpcion vo = new VOOpcion();
				vo.setCodigo(rs.getInt(1));
				vo.setModulo(rs.getInt(2));
				vo.setNombre(rs.getString(3));
				vo.setPagina(rs.getString(4));
				listaOpcion.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		
		return listaOpcion;
	}
	@POST
	@Path("/obtenerStock")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOProducto> obtenerStock(VOProducto vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="SELECT (select sucu_nom_sucu from saesucu where sucu_cod_empr=1 and sucu_cod_sucu=saeprbo.prbo_cod_sucu),TO_NUMBER(prbo_dis_prod) FROM saeprbo where prbo_cod_empr=1 and prbo_cod_bode not in (36,38,39) and prbo_cod_sucu > 1 and prbo_cod_prod ='" + vo.getCodigo() + "'";
		
		List<VOProducto> listaProducto= new ArrayList<VOProducto>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOProducto voi = new VOProducto();
				voi.setLocal(rs.getString(1));
				voi.setStock(rs.getInt(2));
				listaProducto.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaProducto;
	}
	@POST
	@Path("/obtenerNomProd")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOProducto> obtenerNomProd(VOProducto vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="SELECT trim(prod_des_prod) From saeprod where prod_cod_empr=1 and prod_cod_sucu=1 and prod_cod_prod ='" + vo.getCodigo() + "'";
		
		List<VOProducto> listaProducto= new ArrayList<VOProducto>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOProducto voi = new VOProducto();
				voi.setNombre(rs.getString(1));
				listaProducto.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaProducto;
	}
	@POST
	@Path("/obtenerClientes")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOCliente> obtenerClientes(VOCliente vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="SELECT FIRST 1 clpv_cod_clpv,clpv_nom_clpv,clpv_apel_clpv,clpv_ruc_clpv,clpv_fec_naci,clpv_dir_clpv,clpv_mail_clpv,"
				+ "clpv_telf_clpv,clpv_celu_clpv,clpv_est_clpv FROM saeclpv"
				+ " where clpv_cod_empr=1 and grpv_cod_grpv='CA' and clpv_clopv_clpv='CL'"
				+ " and clpv_ruc_clpv='" + vo.getRuc() + "'" ;
		
		List<VOCliente> listaCliente= new ArrayList<VOCliente>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOCliente voi = new VOCliente();
				voi.setCodigo(rs.getInt(1));
				voi.setNombre(rs.getString(2));
				voi.setApellido(rs.getString(3));
				voi.setRuc(rs.getString(4));
				voi.setFechaNacimiento(rs.getString(5));
				voi.setDireccion(rs.getString(6));
				voi.setEmail(rs.getString(7));
				voi.setTelefono(rs.getString(8));
				voi.setCelular(rs.getString(9));
				voi.setEstado(rs.getString(10));
				listaCliente.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		
		return listaCliente;
	}
	@POST
	@Path("/actualizaCliente")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean actualizaCliente(VOCliente vo) {
		boolean actualizar = false;
		
		Statement stm= null;
		Connection co=null;
		
		String sql="UPDATE saeclpv set clpv_nom_clpv = '" + vo.getNombre() + "'  ,clpv_apel_clpv = '" + vo.getApellido() + "' ,clpv_dir_clpv = '" + vo.getDireccion() +"'"
				+ ",clpv_mail_clpv = '" + vo.getEmail() + "'  ,clpv_telf_clpv = '" + vo.getTelefono() + "'"
				+ ",clpv_celu_clpv = '" + vo.getCelular() + "' ,clpv_fec_naci = date('" + vo.getFechaNacimiento() + "')"
				+ ",clpv_pref_telf = '" + vo.getPrefiereTelefono() + "' ,clpv_pref_cel = '" +vo.getPrefiereCelular() + "'"
				+ ",clpv_pref_mail = '" + vo.getPrefiereEmail() + "',clpv_pref_dime = '" + vo.getPrefiereDiaMes() +"'"
				+ ",clpv_pref_anio = '" + vo.getPrefiereAnio() + "'"
				+ " where clpv_cod_empr=1 and grpv_cod_grpv='CA' and clpv_clopv_clpv='CL' and clpv_ruc_clpv ='"+vo.getRuc()+ "'"
				+ " and clpv_cod_clpv =" + vo.getCodigo(); 
		System.out.println(sql);
		
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			actualizar = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			//System.out.println("Error: Clase ClienteDaoImple, método registrar");
			e.printStackTrace();
		}
		return actualizar;
	}
	@POST
	@Path("/actualizaClienteNew")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean actualizaClienteNew(VOCliente vo) {
		boolean actualizar = false;
		
		Statement stm= null;
		Connection co=null;
		
		String sql="UPDATE saeenvi set envi_est_mling = '" + vo.getEstado() + "', envi_obs_mling = '" + vo.getDetalle() + "'"
				+ " where envi_cod_pven = "+vo.getCodigo();
		System.out.println(sql);
		
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			actualizar = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			//System.out.println("Error: Clase ClienteDaoImple, método registrar");
			e.printStackTrace();
		}
		return actualizar;
	}
	@POST
	@Path("/creaCliente")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean creaCliente(VOCliente vo) {
		boolean crear = false;
		
		Statement stm= null;
		Connection co=null;
				
		String sql="insert into saeclpv (clpv_cod_sucu,clpv_cod_empr,clpv_cod_ciud,clpv_cod_zona,grpv_cod_grpv,"
				+ "clpv_cod_vend,clpv_clopv_clpv,clpv_nom_clpv,clpv_ruc_clpv,clpv_est_clpv,clpv_iva_clpv,"
				+ "clpv_pre_ven,clpv_fec_des,clpv_fec_has,clpv_fec_reno,clpv_cal_clpv,clpv_fec_naci,"
				+ "clpv_est_civi,clpv_dir_clpv,clpv_mail_clpv,clpv_telf_clpv,clpv_celu_clpv,clpv_apel_clpv,"
				+ "clpv_pref_telf,clpv_pref_cel,clpv_pref_mail,clpv_pref_dime,clpv_pref_anio) "
				+ "values (1,1,1,17,'CA','V999','CL','"+vo.getNombre()+"','"+vo.getRuc()+"','A','S',1.00,"
				+ "date('"+vo.getFechaCrea()+"'),date('"+vo.getFechaCrea()+"'),date('"+vo.getFechaCrea()+"'),'A',"
				+ "date('" + vo.getFechaNacimiento() + "'),'C','" + vo.getDireccion()+"','"+vo.getEmail()+"','"
				+ vo.getTelefono()+"','"+vo.getCelular()+"','"+vo.getApellido()+"','"
				+ vo.getPrefiereTelefono()+"','"+vo.getPrefiereCelular()+"','"+vo.getPrefiereEmail()+"','"+vo.getPrefiereDiaMes()+"','"+vo.getPrefiereAnio()+"')";
				
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			crear = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método crear");
			e.printStackTrace();
		}
		return crear;
	}
	@POST
	@Path("/cargaDatosDist")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean cargaDatosDist(VOVentasDist vo) {
		boolean cargar = false;
		Statement stm= null;
		Connection co=null;
	
		
				String distSql = "Insert into mbldadis (dadis_cod_empr,dadis_anio_dadis,dadis_mes_dadis,dadis_gest_dadis,dadis_dis_dadis,"
						+ "dadis_tipo_dadis,dadis_fech_dadis,dadis_clie_dadis,dadis_cod_prod,dadis_nom_prod,"
						+ "dadis_prend_dadis,dadis_marc_dadis,dadis_talla_dadis,dadis_color_dadis,dadis_segm_dadis,"
						+ "dadis_cant_dadis,dadis_prec_dadis,dadis_neto_dadis,dadis_iva_dadis,dadis_tota_dadis,dadis_cod_clpv,dadis_cod_ciud,factura,cedula,celular,mail,local) " 
						+ " values  "
						+ "(1,"+ vo.getAnio()+",'"+vo.getMes()+"','"+vo.getGestion()+"','"+vo.getDistribuidor()+"','"+vo.getTipo()+"',date('"+vo.getFecha()+"'),'"+vo.getCliente()+"','"
						+ vo.getCodigo() + "','"+vo.getProducto()+"','"+vo.getPrenda()+"','"+vo.getMarca()+"','"+vo.getTalla()+"','"+vo.getColor()+"','"+vo.getSegmento()+"',"
						+ vo.getCantidad() +","+vo.getPrecio()+","+vo.getSubtotal()+","+vo.getIva()+","+vo.getTotal()+","+vo.getCodCliente()+","+vo.getCodCiudad()+","+vo.getFactura()+",'"+vo.getCedula()+"','"+vo.getCelular()+"','"+vo.getMail()+"','"+vo.getLocal()+"')";
				System.out.println(distSql);
			
				try {
					co = con.connectionJDBC();
					stm= co.createStatement();
					stm.execute(distSql);
					cargar = true;
					stm.close();
					co.close();
					
					
				} catch (SQLException e) {
					System.out.println("Error: Clase ClienteDaoImple, Ingresar Datos Distribuidor!");
					e.printStackTrace();
				}
			return cargar;
				}
	@POST
	@Path("/obtenerMP")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VODatosMP> obtenerMP(VOUsuario voi){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select minv_cod_tran,(select tran_des_tran from saetran where tran_cod_empr=1 and tran_cod_tran=saeminv.minv_cod_tran),"
				+ "minv_num_sec,TO_CHAR(minv_fmov),dmov_num_comp,dmov_cod_dmov,dmov_cod_lote,dmov_cod_prod,"
				+ "(select CASE WHEN prod_des_prod is null THEN prod_nom_prod ELSE prod_des_prod END from saeprod where prod_cod_empr=1 and prod_cod_sucu=1 and prod_cod_prod=saedmov.dmov_cod_prod),"
				+ "TO_CHAR(round(dmov_can_dmov,2)) from saedmov,saeminv where dmov_cod_empr=minv_cod_empr"
				+ " and dmov_cod_ejer=minv_cod_ejer and dmov_num_prdo=minv_num_prdo"
				+ " and dmov_num_comp=minv_num_comp and dmov_cod_ccos in (select ccos_cod_ccos from saeccos where ccos_cod_empr=1 and ccos_usua_recibe in (select usua_cod_usua from saeusua where usua_num_ced in (select usua_num_cedula from mblusuario where usuario = '"+voi.getUsuario()+"')))"
				+ " and dmov_esta_recibe is null and dmov_cod_bode in (select bode_cod_bode from saebode where grbo_cod_grbo in "
				+ " (select grbo_cod_grbo from saegrbo where grbo_cod_empr=1 and grbo_cod_grbo=2))" 
				+ " and minv_fmov>='2020-03-01' order by minv_num_comp,dmov_cod_dmov";
			
		List<VODatosMP> listaMP= new ArrayList<VODatosMP>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VODatosMP vo = new VODatosMP();
				vo.setCodigoTran(rs.getString(1));
				vo.setNombreTran(rs.getString(2));
				vo.setNumeroSec(rs.getString(3));
				vo.setFecha(rs.getString(4));
				vo.setNumeroComp(rs.getInt(5));
				vo.setNumeroDmov(rs.getInt(6));
				vo.setLote(rs.getInt(7));
				vo.setCodigoProd(rs.getString(8));
				vo.setNombreProd(rs.getString(9));
				vo.setCantidad(rs.getString(10));
				
				listaMP.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return listaMP;
	}
	@POST
	@Path("/obtenerUsuarioMbl")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOUsuario> obtenerUsuarioMbl(VOUsuario voi){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select nombre_usuario from mblusuario where usuario = '"+voi.getUsuario()+"'";
		System.out.print(sql);
		List<VOUsuario> listaUsuario= new ArrayList<VOUsuario>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOUsuario vo = new VOUsuario();
				vo.setNombre(rs.getString(1));
				listaUsuario.add(vo);
				
			}
			stm.close();
			rs.close();
			co.close();
			
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		return listaUsuario;
	}
	@POST
	@Path("/obtenerEmpresa")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOEmpresa> obtenerEmpresa(VOEmpresa voi){

		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="SELECT empr_cod_empr,empr_nom_empr FROM saeempr where empr_cod_empr = "+voi.getCodigo();
		System.out.print(sql);
		List<VOEmpresa> listaEmpresa= new ArrayList<VOEmpresa>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOEmpresa vo = new VOEmpresa();
				vo.setCodigo(rs.getInt(1));
				vo.setNombre(rs.getString(2));
				listaEmpresa.add(vo);
				
			}
			stm.close();
			rs.close();
			co.close();
			
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
		return listaEmpresa;
	}
	@POST
	@Path("/recibirMP")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean recibirMP(VODatosMP vo) {
		boolean recibir = false;
				
		Statement stm= null;
		Connection co=null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		java.util.Date fecha = new Date();
		String fechaactu = sdf.format(fecha);
		System.out.println (fecha);
		
		
		
		String sql= "update saedmov set dmov_esta_recibe='S', dmov_usuario_recibe='"+vo.getUsuario()+"',dmov_fech_recibe='"+fechaactu+"' where dmov_cod_empr=1 and dmov_cod_sucu=1 and dmov_num_comp="+vo.getNumeroComp()+" and dmov_cod_dmov="+vo.getNumeroDmov()+" and dmov_cod_prod='"+vo.getCodigoProd()+"'";
			  
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			recibir = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, Ingresar Datos Distribuidor!");
			e.printStackTrace();
		}
		return recibir;
	}
	@POST
	@Path("/recibirTodoMP")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean recibirTodoMP(VODatosMP vo) {
		boolean recibir = false;
				
		Statement stm= null;
		Connection co=null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		java.util.Date fecha = new Date();
		String fechaactu = sdf.format(fecha);
		System.out.println (fecha);
		
		
		
		String sql= "update saedmov set dmov_esta_recibe='S', dmov_usuario_recibe='"+vo.getUsuario()+"',dmov_fech_recibe='"+fechaactu+"' where dmov_cod_empr=1 and dmov_cod_sucu=1 and dmov_num_comp="+vo.getNumeroComp();
			  
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			recibir = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, Ingresar Datos Distribuidor!");
			e.printStackTrace();
		}
		return recibir;
	}
	@GET
	@Path("/obtenerSucursal")
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOSucursal> obtenerSucursal(){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select pasu_cod_sucu,(select sucu_nom_sucu from saesucu where sucu_cod_empr=1 and sucu_cod_sucu=saepasu.pasu_cod_sucu),pasu_habi_dscto,pasu_dias_nc" + 
					" from saepasu " +
					" where pasu_cod_empr=1";
			
		List<VOSucursal> listaSucursal= new ArrayList<VOSucursal>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOSucursal vo = new VOSucursal();
				vo.setCodigo(rs.getInt(1));
				vo.setNombre(rs.getString(2));
				vo.setHabilitar(rs.getInt(3));
				vo.setNumdiasncre(rs.getInt(4));
								
				listaSucursal.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return listaSucursal;
	}
	@POST
	@Path("/cambiarEstado")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean cambiarEstado(VOSucursal vo) {
		boolean cambio = false;
				
		Statement stm= null;
		Connection co=null;
		
		String sql= "update saepasu set pasu_habi_dscto="+vo.getHabilitar()+
				" where pasu_cod_sucu="+vo.getCodigo()+" and pasu_cod_empr=1";
		
		System.out.print(sql);
			  
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			cambio = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, Ingresar Datos Distribuidor!");
			e.printStackTrace();
		}
		return cambio;
	}
	@POST
	@Path("/validarLoggin")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOUsuario> validarLoggin(VOUsuario vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="SELECT * FROM mblusuario WHERE usuario = '" + vo.getUsuario() + "'";
		
		List<VOUsuario> listaUsuario= new ArrayList<VOUsuario>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOUsuario voi = new VOUsuario();
				voi.setCodusuario(rs.getInt(1));
				voi.setNombre(rs.getString(2));
				voi.setUsuario(rs.getString(3));
				voi.setPassword(rs.getString(4));
				voi.setEstado(rs.getString(6));
				voi.setCedula(rs.getString(8));
				voi.setCliente(rs.getInt(9));
				voi.setCiudad(rs.getInt(10));
				
				if(voi.getPassword().equals(vo.getPassword())){
					voi.setUservalido(true);
				}
				listaUsuario.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaUsuario;
	}
	
	@POST
	@Path("/datosUsuario")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOUsuario> datosUsuario(VOUsuario vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="SELECT *,(select clpv_nom_clpv from saeclpv where clpv_cod_empr=1 and clpv_cod_clpv=mblusuario.cliente),(select ciud_nom_ciud from saeciud where ciud_cod_ciud=mblusuario.ciudad) FROM mblusuario WHERE codigo_usuario = " + vo.getCodusuario();
		
		List<VOUsuario> listaUsuario= new ArrayList<VOUsuario>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOUsuario voi = new VOUsuario();
				voi.setCodusuario(rs.getInt(1));
				voi.setNombre(rs.getString(2));
				voi.setUsuario(rs.getString(3));
				voi.setPassword(rs.getString(4));
				voi.setEstado(rs.getString(6));
				voi.setCedula(rs.getString(8));
				voi.setCliente(rs.getInt(9));
				voi.setCiudad(rs.getInt(10));
				voi.setLocal(rs.getString(11));
				voi.setNomcliente(rs.getString(12));
				voi.setNomciudad(rs.getString(13));
				if(voi.getPassword().equals(vo.getPassword())){
					voi.setUservalido(true);
				}
				listaUsuario.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaUsuario;
	}
	
	@POST
	@Path("/clienteNew")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOCliente> clienteNew(VOCliente vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="select envi_cod_pven,(select pven_mail_clie from saepven where pven_cod_pven=saeenvi.envi_cod_pven),(select pven_nom_clie||' '||pven_ape_pven from saepven where pven_cod_pven=saeenvi.envi_cod_pven),(select TO_CHAR(pven_fec_pven) from saepven where pven_cod_pven=saeenvi.envi_cod_pven) from saeenvi where envi_est_mling is null and envi_fech_envi>='2020-08-26 00:00:00'";
		
		List<VOCliente> listaCliente= new ArrayList<VOCliente>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOCliente voi = new VOCliente();
				voi.setCodigo(rs.getInt(1));
				voi.setEmail(rs.getString(2));
				voi.setNombre(rs.getString(3));
				voi.setFecha(rs.getString(4));
						
				listaCliente.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaCliente;
	}
	
	@POST
	@Path("/enviarBono")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOBono> enviarBono(VOBono vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="select bono_cod_bono,bono_val_bono,bono_bene_bono,TO_CHAR(bono_fec_venc),bono_tip_pago,bono_num_bono,bono_corr_bono,bono_conc_bono from saebono where bono_corr_bono<>'' and bono_fec_emi = date(CURRENT) and bono_envi_mling is null";
		System.out.println(sql);
		
		List<VOBono> listaBono= new ArrayList<VOBono>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOBono voi = new VOBono();
				voi.setCodigo(rs.getInt(1));
				voi.setValor(rs.getDouble(2));
				voi.setBeneficiario(rs.getString(3));
				voi.setFecha(rs.getString(4));
				voi.setTipo(rs.getString(5));
				voi.setCedula(rs.getString(6));
				voi.setEmail(rs.getString(7));
				voi.setConcepto(rs.getString(8));
									
				listaBono.add(voi);
			}
			System.out.println(listaBono.toString());
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaBono;
	}
	
	@POST
	@Path("/actEnvioBono")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean actEnvioBono(VOBono vo) {
		boolean crear = false;
		
		Statement stm= null;
		Connection co=null;
				
		String sql="update saebono set bono_envi_mling = '" + vo.getEstado() + "',bono_obs_mling = '" + vo.getObservacion() + "'" +
				" where bono_cod_bono = '" + vo.getCodigo() + "'";
		System.out.println(sql);
		
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			crear = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método crear");
			e.printStackTrace();
		}
		return crear;
	}
	
	@POST
	@Path("/clienteEncuesta")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOCliente> clienteEncuesta(VOCliente vo){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
				
		String sql="select pven_cod_pven,pven_cod_empr,pven_cod_sucu,pven_fec_pven,pven_nom_clie||' '||pven_ape_pven,pven_mail_clie from saepven where pven_cod_empr=1 and pven_fec_pven = date(CURRENT) and pven_cod_sucu in (select pasu_cod_sucu from saepasu where pasu_cod_empr=1 and pasu_mling_satis='S') and pven_mail_clie <> '' and pven_cod_pven not in (select satis_cod_pven from saesatis where satis_cod_empr=1 and satis_fec_satis=date(CURRENT))";
		
		List<VOCliente> listaCliente= new ArrayList<VOCliente>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOCliente voi = new VOCliente();
				voi.setCodigo(rs.getInt(1));
				voi.setEmpresa(rs.getInt(2));
				voi.setSucursal(rs.getInt(3));
				voi.setFecha(rs.getString(4));
				voi.setName(rs.getString(5));
				voi.setEmail(rs.getString(6));
									
				listaCliente.add(voi);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método obtener");
			e.printStackTrace();
		}
			
		return listaCliente;
	}	
	
	@POST
	@Path("/creaSatisfaccion")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean creaSatisfaccion(VOCliente vo) {
		boolean crear = false;
		
		Statement stm= null;
		Connection co=null;
				
		String sql="insert into saesatis (satis_cod_empr,satis_cod_sucu,satis_cod_pven,satis_fec_satis,satis_est_satis,satis_obs_satis) "
				+ "values ("+vo.getEmpresa()+","+vo.getSucursal()+","+vo.getCodigo()+",date(CURRENT),'"+vo.getEstado()+"','RF | '||TO_CHAR(CURRENT))";
		System.out.println(sql);
		
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			crear = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, método crear");
			e.printStackTrace();
		}
		return crear;
	}
	
	@POST
	@Path("/obtenerCcosto")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOCcosto> obtenerCcosto(VOUsuario voi){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select ccos_nom_ccos from saeccos where ccos_cod_empr=1 and " +
		" ccos_usua_recibe in (select usua_cod_usua from saeusua where usua_num_ced in "+
		"(select usua_num_cedula from mblusuario where usuario = '"+voi.getUsuario()+"'))";
			
		List<VOCcosto> listaCcosto= new ArrayList<VOCcosto>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOCcosto vo = new VOCcosto();
				vo.setNombre(rs.getString(1));
								
				listaCcosto.add(vo);
		}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return listaCcosto;
	}
	@GET
	@Path("/obtenerCodProd")
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOProducto> obtenerCodProd(){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select ccodi_cod_ccodi,ccodi_des_ccodi from saeccodi "+
				" where ccodi_cod_ccodi in (select cconti_cod_ccodi from saecconti where cconti_est_cconti='A') "+
				" order by 2";
			
		List<VOProducto> listaProd= new ArrayList<VOProducto>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOProducto vo = new VOProducto();
				vo.setCodigoProd(rs.getInt(1));
				vo.setNombre(rs.getString(2));
											
				listaProd.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return listaProd;
	}
	@POST
	@Path("/detalleConti")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VoDconti> detalleConti(VOProducto voi){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select dconti_cod_dconti,(select dpto_des_dpto from saedpto where dpto_cod_empr=1 and " +
				" dpto_cod_dpto=saedconti.dconti_cod_dpto),TO_CHAR(round(dconti_tiem_dconti,6)) from saedconti " +
				" where dconti_cod_cconti in (select cconti_cod_cconti from saecconti where cconti_est_cconti='A' "+
				" and cconti_cod_empr=1 and cconti_cod_ccodi="+voi.getCodigoProd()+")";
			
		List<VoDconti> listaDconti= new ArrayList<VoDconti>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VoDconti vo = new VoDconti();
				vo.setCodigo(rs.getInt(1));
				vo.setArea(rs.getString(2));
				vo.setTiempo(rs.getString(3));
								
				listaDconti.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return listaDconti;
	}
	@POST
	@Path("/obtenerCinope")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<VOCinope> obtenerCinope(VoDconti voi){
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql="select TO_CHAR(cinope_fila_cinope),cinope_cod_opera,cinope_des_opera,TO_CHAR(round(cinope_cant_cinope,4)),cinope_det_cinope," +
			" TO_CHAR(cinope_pred_cinope),TO_CHAR(round(cinope_sam_cinope,4)),TO_CHAR(round(cinope_subt_cinope,4)) from saecinope "+
			" where cinope_cod_empr=1 and cinope_cod_dconti="+voi.getCodigo();
			
		List<VOCinope> listaCinope= new ArrayList<VOCinope>();
		
		try {			
			co = con.connectionJDBC();
			stm = co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				VOCinope vo = new VOCinope();
				vo.setFila(rs.getString(1));
				vo.setCodOpera(rs.getInt(2));
				vo.setNomOpera(rs.getString(3));
				vo.setCantidad(rs.getString(4));
				vo.setDetalle(rs.getString(5));
				vo.setAntecesor(rs.getString(6));
				vo.setSam(rs.getString(7));
				vo.setSubtotal(rs.getString(8));
								
				listaCinope.add(vo);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
		}
		
		return listaCinope;
	}
	@POST
	@Path("/actualizarDias")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public boolean actualizarDias(VOSucursal vo) {
		boolean actualizar = false;
				
		Statement stm= null;
		Connection co=null;
		
		String sql= "update saepasu set pasu_dias_nc="+vo.getNumdiasncre()+
				" where pasu_cod_sucu="+vo.getCodigo()+" and pasu_cod_empr=1";
		
		System.out.print(sql);
			  
		try {
			co = con.connectionJDBC();
			stm= co.createStatement();
			stm.execute(sql);
			actualizar = true;
			stm.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ClienteDaoImple, Ingresar Datos Distribuidor!");
			e.printStackTrace();
		}
		return actualizar;
	}

}
