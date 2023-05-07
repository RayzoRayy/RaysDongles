package net.minecraft.client.model;

import net.minecraft.client.animation.definitions.SnifferAnimation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnifferModel<T extends Sniffer> extends AgeableHierarchicalModel<T> {
   private static final float WALK_ANIMATION_SPEED_FACTOR = 9000.0F;
   private static final float MAX_WALK_ANIMATION_SPEED = 1.0F;
   private static final float PANIC_ANIMATION_FACTOR = 2.0F;
   private final ModelPart root;
   private final ModelPart head;

   public SnifferModel(ModelPart p_272867_) {
      super(0.5F, 24.0F);
      this.root = p_272867_.getChild("root");
      this.head = this.root.getChild("bone").getChild("body").getChild("head");
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
      PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
      PartDefinition partdefinition2 = partdefinition1.addOrReplaceChild("body", CubeListBuilder.create().texOffs(62, 68).addBox(-12.5F, -14.0F, -20.0F, 25.0F, 29.0F, 40.0F, new CubeDeformation(0.0F)).texOffs(62, 0).addBox(-12.5F, -14.0F, -20.0F, 25.0F, 24.0F, 40.0F, new CubeDeformation(0.5F)).texOffs(87, 68).addBox(-12.5F, 12.0F, -20.0F, 25.0F, 0.0F, 40.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
      partdefinition1.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(32, 87).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 10.0F, -15.0F));
      partdefinition1.addOrReplaceChild("right_mid_leg", CubeListBuilder.create().texOffs(32, 105).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 10.0F, 0.0F));
      partdefinition1.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(32, 123).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.5F, 10.0F, 15.0F));
      partdefinition1.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 87).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 10.0F, -15.0F));
      partdefinition1.addOrReplaceChild("left_mid_leg", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 10.0F, 0.0F));
      partdefinition1.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 123).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 10.0F, 15.0F));
      PartDefinition partdefinition3 = partdefinition2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 15).addBox(-6.5F, -7.5F, -11.5F, 13.0F, 18.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(8, 4).addBox(-6.5F, 7.5F, -11.5F, 13.0F, 0.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.5F, -19.5F));
      partdefinition3.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(2, 0).addBox(0.0F, 0.0F, -3.0F, 1.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -7.5F, -4.5F));
      partdefinition3.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -7.5F, -4.5F));
      partdefinition3.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(10, 45).addBox(-6.5F, -2.0F, -9.0F, 13.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, -11.5F));
      partdefinition3.addOrReplaceChild("lower_beak", CubeListBuilder.create().texOffs(10, 57).addBox(-6.5F, -7.0F, -8.0F, 13.0F, 12.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5F, -12.5F));
      return LayerDefinition.create(meshdefinition, 192, 192);
   }

   public void setupAnim(T p_273213_, float p_273252_, float p_273344_, float p_272633_, float p_272774_, float p_273206_) {
      this.root().getAllParts().forEach(ModelPart::resetPose);
      this.head.xRot = p_273206_ * ((float)Math.PI / 180F);
      this.head.yRot = p_272774_ * ((float)Math.PI / 180F);
      float f = Math.min((float)p_273213_.getDeltaMovement().horizontalDistanceSqr() * 9000.0F, 1.0F);
      float f1 = f * 2.0F;
      this.animate(p_273213_.walkingAnimationState, SnifferAnimation.SNIFFER_WALK, p_272633_, f);
      this.animate(p_273213_.panicAnimationState, SnifferAnimation.SNIFFER_WALK, p_272633_, f1);
      this.animate(p_273213_.diggingAnimationState, SnifferAnimation.SNIFFER_DIG, p_272633_);
      this.animate(p_273213_.searchingAnimationState, SnifferAnimation.SNIFFER_SNIFF_SEARCH, p_272633_, f);
      this.animate(p_273213_.sniffingAnimationState, SnifferAnimation.SNIFFER_LONGSNIFF, p_272633_);
      this.animate(p_273213_.risingAnimationState, SnifferAnimation.SNIFFER_STAND_UP, p_272633_);
      this.animate(p_273213_.feelingHappyAnimationState, SnifferAnimation.SNIFFER_HAPPY, p_272633_);
      this.animate(p_273213_.scentingAnimationState, SnifferAnimation.SNIFFER_SNIFFSNIFF, p_272633_);
   }

   public ModelPart root() {
      return this.root;
   }
}