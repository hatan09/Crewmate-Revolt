package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import data.Gun;
import data.User;
import gfx.ImgAssets;
import main.GUI_Login;
import main.Handler;
import ui.UIImageButton;
import ui.UILabel;
import ui.UIManager;
import ui.UIString;

public class ShopState extends State {
	public static final int SLOT_WIDTH = 0, SLOT_HEIGHT = 0;
	private int chosenGun;
	
	//guns
	private UIImageButton DE, DOUBLE_DE, UZI, UMP, M4A1, M16, AK47, COMING;
	
	//btn
	private UIImageButton equipPri, equipSec, buy, back;
	
	//title
	private UIString gp, price;
	
	//Label
	private UILabel ownDE, ownDDE, ownUZI, ownUMP, ownM4A1, ownM16, ownAK, circle, gpSlot, priceSlot, equip1, equip2;

	public ShopState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);

		DE = new UIImageButton(114, 215, ImgAssets.shop_de.getWidth(), ImgAssets.shop_de.getHeight(), new BufferedImage[] {ImgAssets.shop_de, ImgAssets.shop_de}) {
			@Override
			public void onClick() {
				chosenGun = Gun.DESERT_EAGLE.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.DESERT_EAGLE.getId()))) {
					price.setString(Integer.toString(Gun.DESERT_EAGLE.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownDE = new UILabel(DE.getX(), DE.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		DOUBLE_DE = new UIImageButton(551, 215, ImgAssets.shop_double.getWidth(), ImgAssets.shop_double.getHeight(), new BufferedImage[] {ImgAssets.shop_double, ImgAssets.shop_double}) {
			@Override
			public void onClick() {
				chosenGun = Gun.DOUBLE_DE.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.DOUBLE_DE.getId()))) {
					price.setString(Integer.toString(Gun.DOUBLE_DE.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownDDE = new UILabel(DOUBLE_DE.getX(), DOUBLE_DE.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		UZI = new UIImageButton(988, 215, ImgAssets.shop_uzi.getWidth(), ImgAssets.shop_uzi.getHeight(), new BufferedImage[] {ImgAssets.shop_uzi, ImgAssets.shop_uzi}) {
			@Override
			public void onClick() {
				chosenGun = Gun.UZI.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.UZI.getId()))) {
					price.setString(Integer.toString(Gun.UZI.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownUZI = new UILabel(UZI.getX(), UZI.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		UMP = new UIImageButton(1425, 215, ImgAssets.shop_ump.getWidth(), ImgAssets.shop_ump.getHeight(), new BufferedImage[] {ImgAssets.shop_ump, ImgAssets.shop_ump}) {
			@Override
			public void onClick() {
				chosenGun = Gun.UMP.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.UMP.getId()))) {
					price.setString(Integer.toString(Gun.UMP.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownUMP = new UILabel(UMP.getX(), UMP.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		M4A1 = new UIImageButton(114, 457, ImgAssets.shop_m4a1.getWidth(), ImgAssets.shop_m4a1.getHeight(), new BufferedImage[] {ImgAssets.shop_m4a1, ImgAssets.shop_m4a1}) {
			@Override
			public void onClick() {
				chosenGun = Gun.M4A1.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.M4A1.getId()))) {
					price.setString(Integer.toString(Gun.M4A1.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownM4A1 = new UILabel(M4A1.getX(), M4A1.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		M16 = new UIImageButton(551, 457, ImgAssets.shop_m16.getWidth(), ImgAssets.shop_m16.getHeight(), new BufferedImage[] {ImgAssets.shop_m16, ImgAssets.shop_m16}) {
			@Override
			public void onClick() {
				chosenGun = Gun.M16.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.M16.getId()))) {
					price.setString(Integer.toString(Gun.M16.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownM16 = new UILabel(M16.getX(), M16.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		AK47 = new UIImageButton(988, 457, ImgAssets.shop_ak.getWidth(), ImgAssets.shop_ak.getHeight(), new BufferedImage[] {ImgAssets.shop_ak, ImgAssets.shop_ak}) {
			@Override
			public void onClick() {
				chosenGun = Gun.AK47.getId();
				
				circle.setX(this.x - (ImgAssets.shop_circle.getWidth() - this.getWidth()) / 2);
				circle.setY(this.y - (ImgAssets.shop_circle.getHeight() - this.getHeight()) / 2);
				
				if(!User.getOwnGuns().contains(Integer.valueOf(Gun.AK47.getId()))) {
					price.setString(Integer.toString(Gun.AK47.getPrice()));
					uiManager.removeUIObject(equipPri);
					uiManager.removeUIObject(equipSec);
					uiManager.addUIObject(buy);
				}
				else {
					price.setString("OWNED");
					uiManager.addUIObject(equipPri);
					uiManager.addUIObject(equipSec);
					uiManager.removeUIObject(buy);
				}
			}
		};
		ownAK = new UILabel(AK47.getX(), AK47.getY(), ImgAssets.shop_own.getWidth(), ImgAssets.shop_own.getHeight(), ImgAssets.shop_own);
		
		COMING = new UIImageButton(1425, 457, ImgAssets.shop_cs.getWidth(), ImgAssets.shop_cs.getHeight(), new BufferedImage[] {ImgAssets.shop_cs, ImgAssets.shop_cs});
		
		buy = new UIImageButton(88, 791, ImgAssets.buy_btn[0].getWidth(), ImgAssets.buy_btn[0].getHeight(), ImgAssets.buy_btn) {
			@Override
			public void onClick() {
				if(User.getGp() >= Gun.getGunAsID(chosenGun).getPrice()) {
					int choice = 0;
					choice = JOptionPane.showConfirmDialog(null, "Buy " + Gun.getGunAsID(chosenGun).getName() + " with " + Gun.getGunAsID(chosenGun).getPrice() + " GP? (You have " + User.getGp() + " GP)");
					if(choice == JOptionPane.OK_OPTION) {
						User.setGp(User.getGp() - Gun.getGunAsID(chosenGun).getPrice());
						User.updateGP();
						User.addOwnGuns(chosenGun);
						JOptionPane.showMessageDialog(null, "Your purchase was successful! Thank you!", "DONE!", JOptionPane.INFORMATION_MESSAGE);
					}
					else JOptionPane.showMessageDialog(null, "Canceled!");
				}
				else {
					JOptionPane.showMessageDialog(null, "You don't gave enough GP!", "COULD NOT BUY!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		};
		
		equipPri = new UIImageButton(1474, 752, ImgAssets.equip_btn[0].getWidth(), ImgAssets.equip_btn[0].getHeight(), ImgAssets.equip_btn) {
			@Override
			public void onClick() {
				User.setPriGun(chosenGun);
			}
		};
		equipSec = new UIImageButton(1474, 915, ImgAssets.equip_btn[0].getWidth(), ImgAssets.equip_btn[0].getHeight(), ImgAssets.equip_btn) {
			@Override
			public void onClick() {
				User.setSecGun(chosenGun);
			}
		};
		
		back = new UIImageButton(114, 12, ImgAssets.back_btn[0].getWidth(), ImgAssets.back_btn[0].getHeight(), ImgAssets.back_btn) {
			@Override
			public void onClick() {
				handler.getGame().setMenuState();
			}
		};
		
		gpSlot = new UILabel(1315, 37, ImgAssets.shop_gp.getWidth(), ImgAssets.shop_gp.getHeight(), ImgAssets.shop_gp);
		priceSlot = new UILabel(1315, 124, ImgAssets.shop_price.getWidth(), ImgAssets.shop_price.getHeight(), ImgAssets.shop_price);
		
		gp = new UIString(1500, 70, 250, 55, GUI_Login.f_acc, Color.black) {
			@Override
			public void update() {
				this.setString(Integer.toString(User.getGp()));
			}
		};
		gp.setString(Integer.toString(User.getGp()));
		price = new UIString(1500, 150, 250, 55, GUI_Login.f_acc, Color.black);
		price.setString("0");
		
		circle = new UILabel(DE.getX(), DE.getY(), ImgAssets.shop_circle.getWidth(), ImgAssets.shop_circle.getHeight(), ImgAssets.shop_circle);
		
		equip1 = new UILabel(0, 0, ImgAssets.shop_equiped.getWidth(), ImgAssets.shop_equiped.getHeight(), ImgAssets.shop_equiped) {
			@Override
			public void update() {
				switch(User.getPriGun()) {
					case 1: {
						this.setX(DE.getX() + DE.getWidth() / 2);
						this.setY(DE.getY() + DE.getHeight() / 2);
						break;
					}
					case 2: {
						this.setX(DOUBLE_DE.getX() + DOUBLE_DE.getWidth() / 2);
						this.setY(DOUBLE_DE.getY() + DOUBLE_DE.getHeight() / 2);
						break;
					}
					case 3: {
						this.setX(UZI.getX() + UZI.getWidth() / 2);
						this.setY(UZI.getY() + UZI.getHeight() / 2);
						break;
					}
					case 4: {
						this.setX(UMP.getX() + UMP.getWidth() / 2);
						this.setY(UMP.getY() + UMP.getHeight() / 2);
						break;
					}
					case 5: {
						this.setX(M4A1.getX() + M4A1.getWidth() / 2);
						this.setY(M4A1.getY() + M4A1.getHeight() / 2);
						break;
					}
					case 6: {
						this.setX(M16.getX() + M16.getWidth() / 2);
						this.setY(M16.getY() + M16.getHeight() / 2);
						break;
					}
					case 7: {
						this.setX(AK47.getX() + AK47.getWidth() / 2);
						this.setY(AK47.getY() + AK47.getHeight() / 2);
						break;
					}
				}
			}
		};
		equip2 = new UILabel(0, 0, ImgAssets.shop_equiped.getWidth(), ImgAssets.shop_equiped.getHeight(), ImgAssets.shop_equiped) {
			@Override
			public void update() {
				switch(User.getSecGun()) {
					case 1: {
						this.setX(DE.getX() + DE.getWidth() / 2);
						this.setY(DE.getY() + DE.getHeight() / 2);
						break;
					}
					case 2: {
						this.setX(DOUBLE_DE.getX() + DOUBLE_DE.getWidth() / 2);
						this.setY(DOUBLE_DE.getY() + DOUBLE_DE.getHeight() / 2);
						break;
					}
					case 3: {
						this.setX(UZI.getX() + UZI.getWidth() / 2);
						this.setY(UZI.getY() + UZI.getHeight() / 2);
						break;
					}
					case 4: {
						this.setX(UMP.getX() + UMP.getWidth() / 2);
						this.setY(UMP.getY() + UMP.getHeight() / 2);
						break;
					}
					case 5: {
						this.setX(M4A1.getX() + M4A1.getWidth() / 2);
						this.setY(M4A1.getY() + M4A1.getHeight() / 2);
						break;
					}
					case 6: {
						this.setX(M16.getX() + M16.getWidth() / 2);
						this.setY(M16.getY() + M16.getHeight() / 2);
						break;
					}
					case 7: {
						this.setX(AK47.getX() + AK47.getWidth() / 2);
						this.setY(AK47.getY() + AK47.getHeight() / 2);
						break;
					}
				}
			}
		};
		
		uiManager.addUIObject(DE);
		uiManager.addUIObject(DOUBLE_DE);
		uiManager.addUIObject(UZI);
		uiManager.addUIObject(UMP);
		uiManager.addUIObject(M4A1);
		uiManager.addUIObject(M16);
		uiManager.addUIObject(AK47);
		uiManager.addUIObject(COMING);
		
		uiManager.addUIObject(gpSlot);
		uiManager.addUIObject(priceSlot);
		uiManager.addUIObject(gp);
		uiManager.addUIObject(price);
		
		uiManager.addUIObject(back);
		
		uiManager.addUIObject(circle);
		uiManager.addUIObject(equip1);
		uiManager.addUIObject(equip2);

		
		DE.onClick();
	}
	
	@Override
	public void setUI() {
		handler.getMouseManager().setUIManager(uiManager);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ImgAssets.shop, 0, 0, ImgAssets.shop.getWidth(), ImgAssets.shop.getHeight(), null);
		
		uiManager.render(g);
	}

	@Override
	public void update() {
		uiManager.removeUIObject(ownDE);
		uiManager.removeUIObject(ownDDE);
		uiManager.removeUIObject(ownUZI);
		uiManager.removeUIObject(ownUMP);
		uiManager.removeUIObject(ownM4A1);
		uiManager.removeUIObject(ownM16);
		uiManager.removeUIObject(ownAK);
		
		for(int i = 0; i < User.getOwnGuns().size(); i++) {
			switch(User.getOwnGuns().get(i)) {
			case 1: {
				uiManager.removeUIObject(ownDE);
				uiManager.addUIObject(ownDE);
				break;
			}
			case 2: {
				uiManager.removeUIObject(ownDDE);
				uiManager.addUIObject(ownDDE);
				break;
			}
			case 3: {
				uiManager.removeUIObject(ownUZI);
				uiManager.addUIObject(ownUZI);
				break;
			}
			case 4: {
				uiManager.removeUIObject(ownUMP);
				uiManager.addUIObject(ownUMP);
				break;
			}
			case 5: {
				uiManager.removeUIObject(ownM4A1);
				uiManager.addUIObject(ownM4A1);
				break;
			}
			case 6: {
				uiManager.removeUIObject(ownM16);
				uiManager.addUIObject(ownM16);
				break;
			}
			case 7: {
				uiManager.removeUIObject(ownAK);
				uiManager.addUIObject(ownAK);
				break;
			}
		}
		}
		
		uiManager.update();
	}

}
